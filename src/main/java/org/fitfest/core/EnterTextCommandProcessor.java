package org.fitfest.core;

import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JTextComponentFixture;

public class EnterTextCommandProcessor implements CommandProcessor
{

    @Override
    public String getCommandString()
    {
        return "enterText";
    }

    @Override
    public void handleRow( final FrameFixture window, final RowHandler rowHandler )
    {
        final JTextComponentFixture textBox = window.textBox( rowHandler.getText( 1 ) );
        if ( textBox.text().length() != 0 ) textBox.deleteText();
        textBox.enterText( rowHandler.getText( 2 ) );
        rowHandler.right( 2 );
    }

}
