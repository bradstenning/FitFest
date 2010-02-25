package org.fitfest.core;

import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JComboBoxFixture;

public class SelectComboBoxItemCommandProcessor implements CommandProcessor
{

    @Override
    public String getCommandString()
    {
        return "selectComboBoxItem";
    }

    @Override
    public void handleRow( final FrameFixture window, final RowHandler rowHandler )
    {
        final JComboBoxFixture comboBoxFixture = window.comboBox( rowHandler.getText( 1 ) );
        comboBoxFixture.selectItem( rowHandler.getText( 2 ) );
        rowHandler.right( 2 );

    }

}
