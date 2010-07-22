package org.fitfest.core;

import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JLabelFixture;

public class CheckLabelCommandProcessor implements CommandProcessor
{

    @Override
    public String getCommandString()
    {
        return "checkLabel";
    }

    @Override
    public void handleRow( final FrameFixture window, final RowHandler rowHandler )
    {
        final JLabelFixture label = window.label( rowHandler.getText( 1 ) );
        try
        {
            label.requireText( rowHandler.getText( 2 ) );
            rowHandler.right( 2 );
        }
        catch ( final AssertionError e )
        {
            rowHandler.wrong( 2, label.text() );
        }

    }

}
