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
    
    public abstract String actual(FrameFixture window, RowHandler rowHandler, F fixture);
    
    public void handleException( FrameFixture window, RowHandler rowHandler, F fixture, Throwable e )
    {
        if(e instanceof AssertionError)
        {
            rowHandler.wrong( 2, actual(window, rowHandler, fixture) );
        }
        else
        {
            rowHandler.wrong( 2, e );
        }
    }
    
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
                rowHandler.wrong( 2, actual(window, rowHandler, fixture) );
            }
        }
        catch(Throwable e)
        {
            handleException(window, rowHandler, fixture, e);
        }
    }
}