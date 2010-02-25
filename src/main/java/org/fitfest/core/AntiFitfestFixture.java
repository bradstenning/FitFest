package org.fitfest.core;

public class AntiFitfestFixture extends FitfestFixture
{

    @Override
    protected void right( int row, int column )
    {
        super.wrong( row, column );
    }

    @Override
    protected void wrong( int row, int column, String actual )
    {
        super.right( row, column );
    }

    @Override
    protected void wrong( int row, int column )
    {
        super.right( row, column );
    }

}
