package org.fitfest.core.commandprocessor;

import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JTextComponentFixture;
import org.fitfest.core.AbstractCommandProcessor;
import org.fitfest.core.RowHandler;

public class EnterTextCommandProcessor extends AbstractCommandProcessor<JTextComponentFixture>
{

    @Override
    public String getCommandString()
    {
        return "enterText";
    }

    @Override
    public JTextComponentFixture findFixture( FrameFixture window, RowHandler rowHandler )
    {
        return window.textBox( rowHandler.getText( 1 ) );
    }

    @Override
    public boolean evaluate( RowHandler rowHandler, JTextComponentFixture fixture )
    {
        if ( fixture.text().length() != 0 ) fixture.deleteText();
        fixture.enterText( rowHandler.getText( 2 ) );
        return true;
    }

    @Override
    public String actual( JTextComponentFixture fixture )
    {
        return null;
    }

}
