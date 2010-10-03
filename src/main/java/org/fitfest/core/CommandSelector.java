package org.fitfest.core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.fitfest.core.commandprocessor.CommandProcessor;

public class CommandSelector
{
    private final Map<String, CommandProcessor> commandHandlers = new HashMap<String, CommandProcessor>();

    private void addCommandProcessor( CommandProcessor commandProcessor )
    {
//        System.out.println(commandProcessor.getClass());
        commandHandlers.put( commandProcessor.getCommandString(), commandProcessor );
    }

    private void addClass(String className)
    {
        try
        {
            Class<?> clazz = Class.forName( className );
            if(CommandProcessor.class.isAssignableFrom( clazz )
                    && !Modifier.isAbstract( clazz.getModifiers() )
                    && !clazz.isInterface() )
            {
                addCommandProcessor( (CommandProcessor) clazz.newInstance() );
            }
        }
        catch ( ClassNotFoundException e )
        {
        }
        catch ( InstantiationException e )
        {
        }
        catch ( IllegalAccessException e )
        {
        }
    }
    
    private void addClassesInDirectory( File parent, String parentPackage )
    {
        for(File child : parent.listFiles())
        {
            if(child.isDirectory())
            {
                addClassesInDirectory( child, parentPackage.equals( "" ) ? child.getName() : parentPackage + '.' + child.getName() );
            }
            else if(child.isFile() && child.getName().endsWith( ".class" ))
            {
                addClass( parentPackage.equals( "" ) ? child.getName().substring( 0, ".class".length() ) : parentPackage + '.' + child.getName().substring( 0, child.getName().length() - ".class".length() ) );
            }
        }
    }

    public CommandSelector()
    {
        for ( String path : System.getProperty( "java.class.path" ).split( System.getProperty( "path.separator" ) ) )
        {
            File pathElement = new File(path);
            
            if(pathElement.isDirectory())
            {
                addClassesInDirectory(pathElement, "");
            }
            else if(pathElement.isFile() && (path.endsWith( ".jar" ) || path.endsWith( ".zip" )) )
            {
                try
                {
                    JarFile file = new JarFile( path );
                    Manifest manifest = file.getManifest();
                    for ( String fileName : manifest.getEntries().keySet() )
                    {
                        if ( "true".equalsIgnoreCase( manifest.getAttributes( fileName ).getValue( "org-fitfest-core-CommandProcessor" ) ) )
                        {
                            if(fileName.endsWith( ".class" ))
                            {
                                addClass(fileName.substring( 0, fileName.length() - ".class".length() ).replaceAll( "/", "." ));
                            }
                            else
                            {
                                CommandFinder finder = new CommandFinder();
                                for(CommandProcessor commandProcessor : finder.find( fileName.replaceAll( "/", "." ) ) )
                                {
                                    addCommandProcessor( commandProcessor );
                                }
                            }
                        }
                    }
                }
                catch(IOException e)
                {
                    
                }
            }
        }
    }

    public static void main( String[] args )
    {
        new CommandSelector();
    }

    public CommandProcessor getCommandHandler( String text )
    {
        return commandHandlers.get( text );
    }
}
