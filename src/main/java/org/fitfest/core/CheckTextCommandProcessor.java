package org.fitfest.core;

import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JTextComponentFixture;

public class CheckTextCommandProcessor implements CommandProcessor
{

    @Override
    public String getCommandString()
    {
        return "checkText";
    }

    @Override
    public void handleRow( final FrameFixture window, final RowHandler rowHandler )
    {
        final JTextComponentFixture textBox = window.textBox( rowHandler.getText( 1 ) );
        try
        {
            textBox.requireText( rowHandler.getText( 2 ) );
            rowHandler.right( 2 );
        }
        catch ( final AssertionError e )
        {
            rowHandler.wrong( 2, textBox.text() );
        }

    }

}
