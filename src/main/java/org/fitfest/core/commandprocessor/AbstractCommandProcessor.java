package org.fitfest.core.commandprocessor;

import org.fest.swing.fixture.FrameFixture;
import org.fitfest.core.RowHandler;

public abstract class AbstractCommandProcessor<F> implements CommandProcessor
{
    public AbstractCommandProcessor()
    {
        super();
    }
    /** column to be marked wrong after a failure */
    protected int m_errorColumn = 2;
    
    public abstract F findFixture(FrameFixture window, RowHandler rowHandler);
    
    public abstract boolean evaluate(RowHandler rowHandler, F fixture);
    
    public abstract String actual(F fixture);
    
    public void success(RowHandler rowHandler)
    {
        rowHandler.right( m_errorColumn );
    }
    
    public void failure(RowHandler rowHandler, F fixture)
    {
        rowHandler.wrong( m_errorColumn, actual(fixture) );
    }
    
    public void error(RowHandler rowHandler, Throwable e)
    {
        rowHandler.wrong( m_errorColumn, e );
    }
    
    public void handleException( FrameFixture window, RowHandler rowHandler, F fixture, Throwable e )
    {
        if(e instanceof AssertionError)
        {
            failure(rowHandler, fixture);
        }
        else
        {
            error(rowHandler, e);
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
                success( rowHandler );
            }
            else
            {
                failure( rowHandler, fixture );
            }
        }
        catch(Throwable e)
        {
            handleException(window, rowHandler, fixture, e);
        }
    }
}