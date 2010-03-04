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
    private FrameFixture window = null;
    private final CommandSelector commandHandlers = new CommandSelector();
    private Robot m_robot;
    private ScreenshotTaker screenshotTaker = new ScreenshotTaker();
    private SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyyMMddHHmmssSSS");

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
        if( args.length < 2 ) return;
        
        m_robot = BasicRobot.robotWithCurrentAwtHierarchy();

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
        if ( m_robot != null ) m_robot.cleanUpWithoutDisposingWindows();

    }

    @Override
    protected void wrong( int row, int column, String actual )
    {
        super.wrong( row, column, actual );
        takeScreenshot( row, column );
    }

    protected void takeScreenshot( int row, int column )
    {
        takeScreenshot(getCell(row, column));
    }

    protected void takeScreenshot( Parse cell )
    {
        File directory = new File(FITNESSE_ROOT+SCREENSHOT_HTML_PATH);
        if(directory.exists() && directory.isDirectory() || directory.mkdir())
        {
            String filename = TIMESTAMP_FORMAT.format( new Date() );
            String screenshot = SCREENSHOT_HTML_PATH + filename + ".png";
            screenshotTaker.saveComponentAsPng( window.component(), FITNESSE_ROOT+SCREENSHOT_HTML_PATH + filename + ".png" );
            cell.addToBody( "<hr><a href='"+screenshot+"'>screenshot<a>" );
        }
    }
}
