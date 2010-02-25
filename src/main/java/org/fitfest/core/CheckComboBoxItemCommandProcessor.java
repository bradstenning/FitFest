package org.fitfest.core;

import javax.swing.JComboBox;

import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JComboBoxFixture;

public class CheckComboBoxItemCommandProcessor implements CommandProcessor
{

    @Override
    public String getCommandString()
    {
        return "checkComboBoxItem";
    }

    @Override
    public void handleRow( final FrameFixture window, final RowHandler rowHandler )
    {
        final JComboBoxFixture comboBoxFixture = window.comboBox( rowHandler.getText( 1 ) );
        try
        {
            comboBoxFixture.requireSelection( rowHandler.getText( 2 ) );
            rowHandler.right( 2 );
        }
        catch ( final AssertionError e )
        {
            rowHandler.wrong( 2, comboBoxFixture.targetCastedTo( JComboBox.class ).getSelectedItem().toString() );
        }

    }

}
