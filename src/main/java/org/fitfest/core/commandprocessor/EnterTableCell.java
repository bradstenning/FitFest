package org.fitfest.core.commandprocessor;

import org.fest.swing.fixture.JTableCellFixture;
import org.fitfest.core.RowHandler;

public class EnterTableCell extends AbstractTableCellCommandProcessor
{

    @Override
    public String getCommandString()
    {
        return "enterTableCell";
    }

    @Override
    public boolean evaluate( final RowHandler rowHandler, final JTableCellFixture fixture )
    {
        fixture.enterValue( rowHandler.getText( 4 ) );
        return true;
    }

}
