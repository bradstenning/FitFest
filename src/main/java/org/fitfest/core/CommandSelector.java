package org.fitfest.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.fitfest.core.commandprocessor.BackgroundColor;
import org.fitfest.core.commandprocessor.CheckComboBoxItem;
import org.fitfest.core.commandprocessor.CheckLabel;
import org.fitfest.core.commandprocessor.CheckTableCell;
import org.fitfest.core.commandprocessor.CheckText;
import org.fitfest.core.commandprocessor.Click;
import org.fitfest.core.commandprocessor.CommandProcessor;
import org.fitfest.core.commandprocessor.EnterText;
import org.fitfest.core.commandprocessor.Screenshot;
import org.fitfest.core.commandprocessor.SelectComboBoxItem;
import org.fitfest.core.commandprocessor.SelectComboBoxItemRegex;
import org.fitfest.core.commandprocessor.Sleep;

public class CommandSelector
{
    private final Map<String, CommandProcessor> commandHandlers = new HashMap<String, CommandProcessor>();

    public CommandSelector()
    {
        addCommandProcessor( new Click() );
        addCommandProcessor( new EnterText() );
        addCommandProcessor( new CheckText() );
        addCommandProcessor( new SelectComboBoxItem() );
        addCommandProcessor( new SelectComboBoxItemRegex() );
        addCommandProcessor( new CheckComboBoxItem() );
        addCommandProcessor( new Sleep() );
        addCommandProcessor( new BackgroundColor() );
        addCommandProcessor( new Screenshot() );
        addCommandProcessor( new CheckTableCell() );
        addCommandProcessor( new CheckLabel() );

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
        catch ( IOException e )
        {
            e.printStackTrace();
        }
        catch ( ClassNotFoundException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch ( InstantiationException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch ( IllegalAccessException e )
        {
            // TODO Auto-generated catch block
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
