package org.fitfest.core.commandprocessor;

import org.fest.swing.fixture.JTableCellFixture;
import org.fitfest.core.RowHandler;

public class CheckTableCell extends AbstractTableCellCommandProcessor
{

    @Override
    public String getCommandString()
    {
        return "checkTableCell";
    }

    @Override
    public boolean evaluate( RowHandler rowHandler, JTableCellFixture fixture )
    {
        fixture.requireValue( rowHandler.getText( 4 ) );
        return true;
    }

}
