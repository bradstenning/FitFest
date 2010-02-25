package org.fitfest.core;

import org.fest.swing.fixture.FrameFixture;

public class SleepCommandProcessor implements CommandProcessor
{

    @Override
    public String getCommandString()
    {
        return "sleep";
    }

    @Override
    public void handleRow( final FrameFixture window, final RowHandler rowHandler )
    {
        try
        {
            Thread.sleep( Integer.valueOf( rowHandler.getText( 1 ) ) );
            rowHandler.right( 1 );
        }
        catch ( final Exception e )
        {
            rowHandler.wrong( 1, e.toString() );
        }
    }

}
