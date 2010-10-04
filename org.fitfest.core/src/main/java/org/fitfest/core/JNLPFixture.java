package org.fitfest.core;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.fest.swing.launcher.ApplicationLauncher;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fitnesse.fixtures.TableFixture;

/**
 *  This fixture will launch an application based on the contents
 *  of the supplied JNLP file. It does not use Web Start and thus
 *  the application runs with the same permissions as the fixture.
 */
public class JNLPFixture extends TableFixture
{

    @Override
    protected void doStaticTable( int rows )
    {
        for ( int i = 0; i < rows; i++ )
        {
            try
            {
                startJnlpApp(this.getText( i, 0 ));
                
                right( i, 0 );
            }
            catch(Exception e)
            {
                System.err.println("Format of the table is:\n\n" +
                "|<url>.jnlp|\n" +
                "\n\n");
                e.printStackTrace();
                wrong(i,0);
            }
        }

    }
    

    /**
     * Start an app from the given jnlp location
     * @param url
     * @throws Exception
     */
    protected void startJnlpApp(String url) throws Exception 
    {
        Document doc = buildJnlpDocument( url );
        
        URL[] urls = parseUrlClassPath( doc );
        
        String mainClass = parseMainClass( doc );
        String[] args = parseApplicationArgs( doc );
        putSystemProperties( doc );
        
        ClassLoader cl = new URLClassLoader( urls );
        Thread.currentThread().setContextClassLoader( cl );

        ApplicationLauncher application = ApplicationLauncher.application( mainClass );
        application.withArgs( args );
        application.start();
        
     }

    private void putSystemProperties( Document doc )
    {
        NodeList argList = doc.getElementsByTagName("property");
        for ( int i = 0; i < argList.getLength(); i++ )
        {
            Element element = (Element) argList.item( i );
            System.setProperty( element.getAttribute( "name" ),
                                element.getAttribute( "value" ) );
        }
    }


    private String[] parseApplicationArgs( Document doc )
    {
        NodeList argList = doc.getElementsByTagName("argument");
        String[] args = new String[argList.getLength()];
        for ( int i = 0; i < argList.getLength(); i++ )
        {
            Element element = (Element) argList.item( i );
            args[i] = element.getNodeValue();
        }
        return args;
    }


    /**
     * Pulls the main-class value from the Doc
     * @param doc
     * @return main-class value
     */
    protected String parseMainClass( Document doc )
    {
        NodeList appdesc = doc.getElementsByTagName("application-desc");
        Node app = appdesc.item( 0 );
        String mainClass = app.getAttributes().getNamedItem( "main-class" ).getNodeValue();
        return mainClass;
    }

    /**
     * Pulls the list of jar locations from the <code>Document</code>
     * @param doc
     * @return
     * @throws MalformedURLException
     */
    protected URL[] parseUrlClassPath( Document doc ) throws MalformedURLException
    {
        String codebase = doc.getDocumentElement().getAttribute( "codebase" );

        NodeList nodeLst = doc.getElementsByTagName("jar");
        StringBuffer stringBuffer = new StringBuffer( codebase );
        URL[] urls = new URL[nodeLst.getLength()];
        for (int i = 0; i < nodeLst.getLength(); i++) 
        {
            Node fstNode = nodeLst.item(i);
            Node namedItem = fstNode.getAttributes().getNamedItem( "href" );
            urls[i] = new URL(stringBuffer.append( "/" ).append( namedItem.getNodeValue() ).toString());
        }
        return urls;
    }

    /**
     * Builds a <code>Document</code> from location of URL
     * @param url
     * @return
     * @throws MalformedURLException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    protected Document buildJnlpDocument( String url ) throws MalformedURLException, ParserConfigurationException, SAXException, IOException
    {
        URL mainURL = new URL( url );
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse( mainURL.openStream() );
        doc.getDocumentElement().normalize();
        return doc;
    }
}
