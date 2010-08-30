package org.fitfest.core.commandprocessor;


import org.fest.swing.fixture.JComboBoxFixture;
import org.fitfest.core.CommandProcessor;
import org.fitfest.core.RowHandler;

public class CheckComboBoxItemCommandProcessor extends ComboBoxAbstractCommandProcessor
{
    @Override
    public String getCommandString()
    {
        return "checkComboBoxItem";
    }

    @Override
    public boolean evaluate( RowHandler rowHandler, JComboBoxFixture fixture )
    {
        fixture.requireSelection( rowHandler.getText( 2 ) );
        return true;
    }

}
