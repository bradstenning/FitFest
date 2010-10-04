package org.fitfest.core.commandprocessor;

import org.fest.swing.fixture.FrameFixture;
import org.fitfest.core.RowHandler;

public class Screenshot implements CommandProcessor
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
