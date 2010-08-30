package org.fitfest.core;


import org.fest.swing.fixture.JComboBoxFixture;

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
