package org.fitfest.core;

import java.io.File;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.fitfest.core.commandprocessor.CommandProcessor;

import sun.misc.Launcher;

/**
 * Looks for classes that implement {@link CommandProcessor} 
 */
public class CommandFinder
{
    /**
     * Find all {@link CommandProcessor} in the given package and return the list of them.
     * @param packageName Example: org.fitfest.core.commandprocessors
     * @return CommandProccessor list (empty list if not found)
     */
    public List<CommandProcessor> find(String packageName) 
    {
        URL url = getUrlForPackage( packageName );
        if(url == null) return Collections.emptyList();
        
        File directory = new File(url.getFile());
        if (!directory.exists()) return Collections.emptyList(); 

        List<CommandProcessor> commandProcessors = new ArrayList<CommandProcessor>();
        for ( String file : directory.list() )
        {
            addIndividualClass( packageName, file, commandProcessors );
        }

        return commandProcessors;
    }

    /**
     * Adds the command processor to the list if it's valid. 
     * @param pckgname
     * @param file
     * @param commandProcessors
     */
    protected void addIndividualClass( String pckgname, String file, List<CommandProcessor> commandProcessors )
    {
        if( !file.endsWith( ".class" )) return; 

        try
        {
            String className = file.substring( 0, file.length() - ".class".length() );
            Class<?> clazz = Class.forName( pckgname + "." + className );
            
            if(Modifier.isAbstract( clazz.getModifiers() ) || clazz.isAnonymousClass()) return;
            
            Object potentialCommand = clazz.newInstance();
            if ( potentialCommand instanceof CommandProcessor )
            {
                commandProcessors.add( (CommandProcessor) potentialCommand );

            }
        }
        catch ( Throwable e )
        {
            // don't care.
            e.printStackTrace();
        }
    }

    /**
     * Changes package name (com.something.else) to a URL that the 
     * class loader will use.
     * @param packageName
     * @return
     */
    protected URL getUrlForPackage(final String packageName )
    {
        // path magic that needs to be done.
        String name = new String(packageName);
        if (!name.startsWith("/"))  // or it will try to add the package name again
        {
            name = "/" + name;
        }        
        name = name.replace('.','/');
        
        return Launcher.class.getResource(name);
    }

    /** 
     * Test method 
     * @param args
     */
    public static void main( String[] args )
    {
        CommandFinder commandFinder = new CommandFinder();
        List<CommandProcessor> find = commandFinder.find("org.fitfest.core.commandprocessor");
        for ( CommandProcessor commandProcessor : find )
        {
            System.out.println(commandProcessor.getClass().getName());
        }
    }

}
