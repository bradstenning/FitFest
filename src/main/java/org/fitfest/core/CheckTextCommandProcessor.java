package org.fitfest.core;

import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JTextComponentFixture;

public class CheckTextCommandProcessor extends AbstractCommandProcessor<JTextComponentFixture>
{

    @Override
    public String getCommandString()
    {
        return "checkText";
    }

    @Override
    public JTextComponentFixture findFixture( FrameFixture window, RowHandler rowHandler )
    {
        return window.textBox( rowHandler.getText( 1 ) );
    }

    @Override
    public boolean evaluate( RowHandler rowHandler, JTextComponentFixture fixture )
    {
        fixture.requireText( rowHandler.getText( 2 ) );
        return true;
    }

    @Override
    public String actual( JTextComponentFixture fixture )
    {
        return fixture.text();
    }

}
