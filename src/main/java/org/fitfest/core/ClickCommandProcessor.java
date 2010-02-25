package org.fitfest.core;

import org.fest.swing.fixture.FrameFixture;

public class ClickCommandProcessor implements CommandProcessor
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
