package org.fitfest.core;

import java.awt.Frame;
import java.util.HashMap;
import java.util.Map;

import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.core.Robot;
import org.fest.swing.finder.WindowFinder;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.launcher.ApplicationLauncher;

import fitnesse.fixtures.TableFixture;

public class FitfestFixture extends TableFixture
{
    private FrameFixture window = null;
    private final Map<String, CommandProcessor> commandHandlers = new HashMap<String, CommandProcessor>();
    private Robot m_robot;

    public FitfestFixture()
    {
        addCommandProcessor( new ClickCommandProcessor() );
        addCommandProcessor( new EnterTextCommandProcessor() );
        addCommandProcessor( new CheckTextCommandProcessor() );
        addCommandProcessor( new SelectComboBoxItemCommandProcessor() );
        addCommandProcessor( new CheckComboBoxItemCommandProcessor() );
        addCommandProcessor( new SleepCommandProcessor() );
    }

    private void addCommandProcessor( final CommandProcessor commandProcessor )
    {
        commandHandlers.put( commandProcessor.getCommandString(), commandProcessor );
    }

    @Override
    protected void doStaticTable( final int rows )
    {
        try
        {
            startapp();

            for ( int i = 0; i < rows; i++ )
            {
                final int row = i;
                final RowHandler rowHandler = new RowHandler()
                {
                    public String getText( final int column )
                    {
                        return FitfestFixture.this.getText( row, column );
                    }

                    public void right( final int column )
                    {
                        FitfestFixture.this.right( row, column );
                    }

                    public void wrong( final int column )
                    {
                        FitfestFixture.this.wrong( row, column );
                    }

                    public void wrong( final int column, final String actual )
                    {
                        FitfestFixture.this.wrong( row, column, actual );
                    }
                };
                try
                {
                    final CommandProcessor commandProcessor = commandHandlers.get( getText( row, 0 ) );
                    if ( commandProcessor == null )
                    {
                        wrong( row, 0, "missing command processor" );
                        continue;
                    }
                    commandProcessor.handleRow( window, rowHandler );

                }
                catch ( final RuntimeException e )
                {
                    wrong( row, 1, e.getMessage() );
                    System.err.println( e );
                }
            }
        }
        finally
        {
            tearDown();
        }
    }

    private void startapp()
    {
        m_robot = BasicRobot.robotWithNewAwtHierarchy();

        ApplicationLauncher.application( args[0] ).start();
        window = WindowFinder.findFrame( new GenericTypeMatcher<Frame>( Frame.class )
        {
            @Override
            protected boolean isMatching( final Frame frame )
            {
                return args[1].equals( frame.getTitle() ) && frame.isShowing();
            }

        } ).using( m_robot );
    }

    private void tearDown()
    {
        if ( m_robot != null ) m_robot.cleanUp();

    }

}
