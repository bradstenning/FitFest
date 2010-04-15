package org.fitfest.core;

import org.fest.swing.launcher.ApplicationLauncher;

import fitnesse.fixtures.TableFixture;

public class JavaAppFixture extends TableFixture
{

    private static final int CLASS_NAME_COLUMN = 0;
    private static final int SYS_PROP_COLUMN = 1;
    private static final int CMD_LINE_ARGS_COLUMN = 2;

    @Override
    protected void doStaticTable( final int rows )
    {
        for ( int i = 0; i < rows; i++ )
        {
            try
            {
                // the crazy if is because if a column is not defined you get the
                // value of the last column defined in the table 
                // this test is a quick hack to make sure that is not going on.
                String[] cmdLineArgs = null;
                if(!(getText( i, CLASS_NAME_COLUMN ).equals( getText(i,SYS_PROP_COLUMN) )&&
                   getText( i, CLASS_NAME_COLUMN ).equals( getText(i,CMD_LINE_ARGS_COLUMN) )))
                {
                    parseSystemProperties( i );
                    cmdLineArgs = parseCmdLineArgs( i );
                }

                ApplicationLauncher application = ApplicationLauncher.application( getText( i, CLASS_NAME_COLUMN ) );
                if(cmdLineArgs != null)
                {
                    application.withArgs( cmdLineArgs );
                }
                application.start();
                
                right( i, CLASS_NAME_COLUMN );
            }
            catch(Exception e)
            {
                System.err.println("Format of the table is:\n\n" +
                "|Class name with main method|SystemProperties=value|CommandLineArg1 CommandLineArg2|\n" +
                "\n\n");
                e.printStackTrace();
                wrong(i,CLASS_NAME_COLUMN);
            }
        }
    }

    private String[] parseCmdLineArgs( int i )
    {
        String[] systemProperties = null;
        try
        {
            String progargs = getText( i, CMD_LINE_ARGS_COLUMN );
            if(progargs != null && progargs.length() > 1)
            {
                systemProperties = progargs.split( " " );
                right(i,CMD_LINE_ARGS_COLUMN);
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            wrong(i,CMD_LINE_ARGS_COLUMN);
        }
        return systemProperties;
    }

    private void parseSystemProperties( int i )
    {
        try
        {
            String sysargs = getText( i, SYS_PROP_COLUMN );
            if(sysargs != null && sysargs.length() > 1)
            {
                String[] systemProperties = sysargs.split( " " );
                for ( int j = 0; j < systemProperties.length; j++ )
                {
                    String[] keyValue = systemProperties[j].split( "=" );
                    if(keyValue.length == 2 )
                        System.setProperty( keyValue[0], keyValue[1] );
                    else
                    {
                        System.err.println("sysargs" +sysargs);
                        wrong(i,SYS_PROP_COLUMN);
                    }
                }
                right(i,SYS_PROP_COLUMN);
            }
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            wrong(i,SYS_PROP_COLUMN);
        }
    }

}
