package org.fitfest.core;

import java.awt.Frame;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.core.Robot;
import org.fest.swing.finder.WindowFinder;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.image.ScreenshotTaker;

import fit.Parse;
import fitnesse.fixtures.TableFixture;

public class FitfestFixture extends TableFixture
{
    private static final String FITNESSE_ROOT = "FitNesseRoot/";
    private static final String SCREENSHOT_HTML_PATH = "files/screenshots/";
    private static FrameFixture window = null;
    private final CommandSelector commandHandlers = new CommandSelector();
    private static Robot m_robot;
    private ScreenshotTaker screenshotTaker = new ScreenshotTaker();
    private SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat( "yyyyMMddHHmmssSSS" );

    @Override
    protected void doStaticTable( final int rows )
    {
        try
        {
            findWindowForTestingIn();

            for ( int i = 0; i < rows; i++ )
            {
                final int row = i;
                final RowHandler rowHandler = new FitfestRowHandler( row );
                try
                {
                    final CommandProcessor commandProcessor = commandHandlers.getCommandHandler( getText( row, 0 ) );
                    if ( commandProcessor == null )
                    {
                        wrong( row, 0, "missing command processor" );
                        continue;
                    }
                    commandProcessor.handleRow( window, rowHandler );

                }
                catch ( final RuntimeException e )
                {
                    wrong( row, 1 );
                    getCell( row, 1 ).addToBody( "<pre>" + e.getMessage() + "</pre>" );
                    System.err.println( e );
                }
            }
        }
        finally
        {
            tearDown();
        }
    }

    private void findWindowForTestingIn()
    {
        if(args.length == 0) return; // Not defied. Window from last run will be used if it exists 
        
        m_robot = BasicRobot.robotWithCurrentAwtHierarchy();

        window = WindowFinder.findFrame( new GenericTypeMatcher<Frame>( Frame.class )
        {
            @Override
            protected boolean isMatching( final Frame frame )
            {
                return args[0].equals( frame.getTitle() ) && frame.isShowing();
            }

        } ).using( m_robot );
    }

    private void tearDown()
    {
        if ( m_robot != null ) m_robot.cleanUpWithoutDisposingWindows();
    }

    @Override
    protected void wrong( int row, int column, String actual )
    {
        super.wrong( row, column, actual );
        getCell( row, column ).addToBody( "<hr>" );
        screenshot( row, column );
    }

    public void wrong( int row, int column, Throwable e )
    {
        super.wrong( row, column );
        getCell( row, column ).addToBody( "<pre>" + e.getMessage() + "</pre>" );
    }
    
    protected void screenshot( int row, int column )
    {
        screenshot( getCell( row, column ) );
    }

    protected void screenshot( Parse cell )
    {
        File directory = new File( FITNESSE_ROOT + SCREENSHOT_HTML_PATH );
        if ( directory.exists() && directory.isDirectory() || directory.mkdir() )
        {
            String filename = TIMESTAMP_FORMAT.format( new Date() );
            String screenshot = SCREENSHOT_HTML_PATH + filename + ".png";
            screenshotTaker.saveComponentAsPng( window.component(), FITNESSE_ROOT + SCREENSHOT_HTML_PATH + filename + ".png" );
            cell.addToBody( "<a href='" + screenshot + "'>screenshot<a>" );
        }
    }

    private final class FitfestRowHandler implements RowHandler
    {
        private final int row;
    
        private FitfestRowHandler( int row )
        {
            this.row = row;
        }
    
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

        @Override
        public void wrong( int column, Throwable e )
        {
            FitfestFixture.this.wrong( row, column, e );
        }
    
        @Override
        public void screenshot( int column )
        {
            FitfestFixture.this.screenshot( row, column );
        }
    }
}
