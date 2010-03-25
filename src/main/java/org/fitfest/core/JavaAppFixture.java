package org.fitfest.core;

import org.fest.swing.launcher.ApplicationLauncher;

import fitnesse.fixtures.TableFixture;

public class JavaAppFixture extends TableFixture
{

    @Override
    protected void doStaticTable( final int rows )
    {
        for ( int i = 0; i < rows; i++ )
        {
            parseSystemProperties( i );
            
            String[] cmdLineArgs = parseCmdLineArgs( i );
            
            ApplicationLauncher application = ApplicationLauncher.application( getText( i, 0 ) );
            if(cmdLineArgs != null)
            {
                application.withArgs( cmdLineArgs );
            }
            application.start();
            right( i, 0 );
        }
    }

    private String[] parseCmdLineArgs( int i )
    {
        String[] systemProperties = null;
        try
        {
            String progargs = getText( i, 2 );
            if(progargs != null && progargs.length() > 1)
            {
                systemProperties = progargs.split( " " );
                right(i,2);
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            wrong(i,2);
        }
        return systemProperties;
    }

    private void parseSystemProperties( int i )
    {
        try
        {
            String sysargs = getText( i, 1 );
            if(sysargs != null && sysargs.length() > 1)
            {
                String[] systemProperties = sysargs.split( " " );
                for ( int j = 0; j < systemProperties.length; j++ )
                {
                    String[] keyValue = systemProperties[j].split( "=" );
                    if(keyValue.length == 2 )
                        System.setProperty( keyValue[0], keyValue[1] );
                    else
                        wrong(i,1);
                }
                right(i,1);
            }
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            wrong(i,1);
        }
    }

}
