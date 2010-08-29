package org.fitfest.core;

import org.fest.swing.fixture.FrameFixture;

public abstract class AbstractCommandProcessor<F> implements CommandProcessor
{
    public AbstractCommandProcessor()
    {
        super();
    }

    public abstract F findFixture(FrameFixture window, RowHandler rowHandler);
    
    public abstract boolean evaluate(RowHandler rowHandler, F fixture);
    
    public abstract String failureMessage(FrameFixture window, RowHandler rowHandler, F fixture);
    
    @Override
    public void handleRow( final FrameFixture window, final RowHandler rowHandler )
    {
        F fixture = findFixture(window, rowHandler);
        try
        {
            if(evaluate(rowHandler, fixture))
            {
                rowHandler.right( 2 );
            }
            else
            {
                rowHandler.wrong( 2, failureMessage(window, rowHandler, fixture) );
            }
        }
        catch(Exception e)
        {
            rowHandler.wrong( 2, e.getMessage() );
        }
    }
}