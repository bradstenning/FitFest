package org.fitfest.core.commandprocessor;

import org.fest.swing.fixture.FrameFixture;
import org.fitfest.core.RowHandler;

public class Click implements CommandProcessor
{
    public String getCommandString()
    {
        return "click";
    }

    public void handleRow( final FrameFixture window, final RowHandler rowHandler )
    {
        window.button( rowHandler.getText( 1 ) ).click();
        rowHandler.right( 1 );
    }
}
