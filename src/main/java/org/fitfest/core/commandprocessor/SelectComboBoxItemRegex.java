package org.fitfest.core;

import java.util.regex.Pattern;

import org.fest.swing.fixture.JComboBoxFixture;

public class SelectComboBoxItemRegexCommandProcessor extends SelectComboBoxItemCommandProcessor
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
