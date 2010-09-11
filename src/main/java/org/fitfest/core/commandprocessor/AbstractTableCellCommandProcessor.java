package org.fitfest.core.commandprocessor;

import static org.fest.swing.data.TableCell.*;

import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JTableCellFixture;
import org.fest.swing.fixture.JTableFixture;
import org.fitfest.core.RowHandler;

public abstract class AbstractTableCellCommandProcessor extends AbstractCommandProcessor<JTableCellFixture>
{

    public AbstractTableCellCommandProcessor()
    {
        m_errorColumn = 4;
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
    public String actual( JTableCellFixture fixture )
    {
        return fixture.value();
    }

}