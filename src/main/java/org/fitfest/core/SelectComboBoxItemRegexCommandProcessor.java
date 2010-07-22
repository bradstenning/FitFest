package org.fitfest.core;

import java.util.regex.Pattern;

import org.fest.swing.exception.LocationUnavailableException;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JComboBoxFixture;

public class SelectComboBoxItemRegexCommandProcessor implements CommandProcessor
{

    @Override
    public String getCommandString()
    {
        return "selectComboBoxItemRegex";
    }

    @Override
    public void handleRow( final FrameFixture window, final RowHandler rowHandler )
    {
        final JComboBoxFixture comboBoxFixture = window.comboBox( rowHandler.getText( 1 ) );
        try
        {
            comboBoxFixture.selectItem( Pattern.compile( rowHandler.getText( 2 ) ) );
            rowHandler.right( 2 );
        }
        catch(LocationUnavailableException e)
        {
            rowHandler.wrong( 2, e.getMessage() );
        }

    }

}
