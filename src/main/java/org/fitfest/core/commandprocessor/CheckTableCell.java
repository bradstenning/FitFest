package org.fitfest.core.commandprocessor;

import static org.fest.swing.data.TableCell.*;

import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JTableCellFixture;
import org.fest.swing.fixture.JTableFixture;
import org.fitfest.core.RowHandler;

public class CheckTableCell extends AbstractCommandProcessor<JTableCellFixture>
{
    public CheckTableCell()
    {
        m_errorColumn = 4;
    }

    @Override
    public String getCommandString()
    {
        return "checkTableCell";
    }

    @Override
    public JTableCellFixture findFixture( FrameFixture window, RowHandler rowHandler )
    {
        JTableFixture table = window.table(rowHandler.getText( 1 ));
        return table.cell(
                row( Integer.valueOf( rowHandler.getText( 2 ) ))
                .column(Integer.valueOf( rowHandler.getText( 3 ) )));
    }

    @Override
    public boolean evaluate( RowHandler rowHandler, JTableCellFixture fixture )
    {
        fixture.requireValue( rowHandler.getText( 4 ) );
        return true;
    }

    @Override
    public String actual( JTableCellFixture fixture )
    {
        return fixture.value();
    }

}
