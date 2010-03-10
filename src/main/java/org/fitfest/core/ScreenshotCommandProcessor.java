package org.fitfest.core;

import org.fest.swing.fixture.FrameFixture;

public class ScreenshotCommandProcessor implements CommandProcessor
{

    @Override
    public String getCommandString()
    {
        return "screenshot";
    }

    @Override
    public void handleRow( FrameFixture window, RowHandler rowHandler )
    {
        rowHandler.screenshot( 2 );
    }
}
