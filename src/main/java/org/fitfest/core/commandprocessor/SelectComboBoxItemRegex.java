package org.fitfest.core.commandprocessor;

import java.util.regex.Pattern;

import org.fest.swing.fixture.JComboBoxFixture;
import org.fitfest.core.RowHandler;

public class SelectComboBoxItemRegex extends SelectComboBoxItem
{
    @Override
    public String getCommandString()
    {
        return "selectComboBoxItemRegex";
    }

    @Override
    public boolean evaluate( RowHandler rowHandler, JComboBoxFixture fixture )
    {
        fixture.selectItem( Pattern.compile( rowHandler.getText( 2 ) ) );
        return true;
    }
}
