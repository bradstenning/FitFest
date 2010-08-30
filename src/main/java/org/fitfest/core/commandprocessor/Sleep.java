package org.fitfest.core.commandprocessor;

import org.fest.swing.fixture.FrameFixture;
import org.fitfest.core.RowHandler;

public class Sleep implements CommandProcessor
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
