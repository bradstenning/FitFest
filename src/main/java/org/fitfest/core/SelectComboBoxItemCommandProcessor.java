package org.fitfest.core;

import java.util.Arrays;

import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JComboBoxFixture;

public class SelectComboBoxItemCommandProcessor extends AbstractCommandProcessor<JComboBoxFixture>
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
    public String failureMessage(FrameFixture window, RowHandler rowHandler, JComboBoxFixture fixture)
    {
        return "Item not found. Available items: " + Arrays.asList( fixture.contents() );
    }
}
