package org.fitfest.core.commandprocessor;

import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JLabelFixture;
import org.fitfest.core.RowHandler;

public class CheckLabel extends AbstractCommandProcessor<JLabelFixture>
{

    @Override
    public String getCommandString()
    {
        return "checkLabel";
    }

    @Override
    public JLabelFixture findFixture( FrameFixture window, RowHandler rowHandler )
    {
        return window.label( rowHandler.getText( 1 ) );
    }

    @Override
    public boolean evaluate( RowHandler rowHandler, JLabelFixture fixture )
    {
        fixture.requireText( rowHandler.getText( 2 ) );
        return true;
    }

    @Override
    public String actual( JLabelFixture fixture )
    {
        return fixture.text();
    }

}
