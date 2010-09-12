package org.fitfest.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.fitfest.core.commandprocessor.CommandProcessor;

public class CommandSelector
{
    private final Map<String, CommandProcessor> commandHandlers = new HashMap<String, CommandProcessor>();

    public CommandSelector()
    {
        CommandFinder commandFinder = new CommandFinder();
        List<CommandProcessor> commandProcessors = commandFinder.find("org.fitfest.core.commandprocessor");
        for ( CommandProcessor commandProcessor : commandProcessors )
        {
            addCommandProcessor( commandProcessor );
        }

        try
        {
            for ( String path : System.getProperty( "java.class.path" ).split( System.getProperty( "path.separator" ) ) )
            {
                if ( path.endsWith( ".jar" ) || path.endsWith( ".zip" ) )
                {
                    JarFile file = new JarFile( path );
                    Manifest manifest = file.getManifest();
                    for ( String classFileName : manifest.getEntries().keySet() )
                    {
                        if ( "true".equalsIgnoreCase( manifest.getAttributes( classFileName ).getValue( "org-fitfest-core-CommandProcessor" ) ) )
                        {
                            Class<?> clazz = Class.forName( classFileName.substring( 0, classFileName.length() - ".class".length() ).replaceAll( "/", "." ) );
                            addCommandProcessor( (CommandProcessor) clazz.newInstance() );
                        }
                    }
                }
            }
        }
        catch ( Throwable e )
        {
            // nothing we can do about it
            e.printStackTrace();
        }
    }

    private void addCommandProcessor( CommandProcessor commandProcessor )
    {
        commandHandlers.put( commandProcessor.getCommandString(), commandProcessor );
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
