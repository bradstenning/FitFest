package org.fitfest.core;

import java.applet.Applet;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.html.HTMLEditorKit.ParserCallback;

import org.fest.swing.launcher.AppletLauncher;

import fitnesse.fixtures.TableFixture;

public class JavaAppletFixture extends TableFixture
{
    private List<String> archive;
    private String code;

    public static class HTMLParse extends HTMLEditorKit
    {
        private static final long serialVersionUID = 8801270170109790276L;

        public HTMLEditorKit.Parser getParser()
        {
            return super.getParser();
        }
    }


    private void tmp( String url ) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        URL mainURL = new URL( url );

        new HTMLParse().getParser().parse( new InputStreamReader( mainURL.openStream() ), new ParserCallback()
        {
            @Override
            public void handleStartTag( Tag t, MutableAttributeSet a, int pos )
            {
                if ( t.equals( Tag.APPLET ) )
                {
                    archive = a.getAttribute( HTML.Attribute.ARCHIVE ) == null ? new ArrayList<String>() : Arrays.asList( a.getAttribute( HTML.Attribute.ARCHIVE ).toString().split( "," ) );
                    if(a.getAttribute( HTML.Attribute.CODEBASE ) != null) archive.add( a.getAttribute( HTML.Attribute.CODEBASE ).toString() );
                    code = a.getAttribute( HTML.Attribute.CODE ).toString();
                }
            }
        }, true );

        URL[] urls = new URL[archive.size()];

        for ( int i = 0; i < urls.length; i++ )
        {
            urls[i] = new URL( url.substring( 0, url.lastIndexOf( File.separatorChar ) + 1 ) + archive.get( i ) );
        }

        ClassLoader cl = new URLClassLoader( urls );
        @SuppressWarnings( "unchecked" )
        Class<? extends Applet> appletClass = (Class<? extends Applet>) cl.loadClass( code.substring( 0, code.length() - ".class".length() ).replaceAll( "/",
                "." ) );
        Thread.currentThread().setContextClassLoader( cl );
        AppletLauncher.applet( appletClass ).start();
    }


    @Override
    protected void doStaticTable( int rows )
    {
        for ( int i = 0; i < rows; i++ )
        {
            try
            {
                tmp(this.getText( i, 0 ));
                
                right( i, 0 );
            }
            catch(Exception e)
            {
                System.err.println("Format of the table is:\n\n" +
                "|url|\n" +
                "\n\n");
                e.printStackTrace();
                wrong(i,0);
            }
        }
    }


    public static void main( String[] args ) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        new JavaAppletFixture().tmp( args[0] );
    }
}