package org.fitfest.core.commandprocessor;

import org.fest.swing.exception.LocationUnavailableException;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JComboBoxFixture;
import org.fitfest.core.AbstractCommandProcessor;
import org.fitfest.core.RowHandler;

public class SelectComboBoxItem extends AbstractCommandProcessor<JComboBoxFixture>
{
    @Override
    public String getCommandString()
    {
        return "selectComboBoxItem";
    }

    @Override
    public JComboBoxFixture findFixture(FrameFixture window, RowHandler rowHandler)
    {
        return window.comboBox( rowHandler.getText( 1 ) );
    }

    @Override
    public boolean evaluate( RowHandler rowHandler, JComboBoxFixture fixture )
    {
        fixture.selectItem( rowHandler.getText( 2 ) );
        return true;
    }

    @Override
    public String actual( JComboBoxFixture fixture )
    {
        return null;
    }
    
    @Override
    public void handleException( FrameFixture window, RowHandler rowHandler, JComboBoxFixture fixture, Throwable e )
    {
        if( e instanceof LocationUnavailableException )
        {
            LocationUnavailableException lue = (LocationUnavailableException)e;
            rowHandler.wrong( 2 );
            rowHandler.append( 2, "<hr> " + e.getMessage() );
        }
        else
        {
            super.handleException( window, rowHandler, fixture, e );
        }
    }
}
