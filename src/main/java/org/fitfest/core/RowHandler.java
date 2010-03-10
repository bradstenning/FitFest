package org.fitfest.core;

public interface RowHandler
{
    public String getText( int i );

    public void right( int column );

    public void wrong( int column );

    public void wrong( int column, String actual );

    public void screenshot( int column );
}
