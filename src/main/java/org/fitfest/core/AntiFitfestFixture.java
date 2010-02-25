package org.fitfest.core;

public class AntiFitfestFixture extends FitfestFixture
{

    @Override
    protected void right( int row, int column )
    {
        // TODO Auto-generated method stub
        super.wrong( row, column );
    }

    @Override
    protected void wrong( int row, int column, String actual )
    {
        // TODO Auto-generated method stub
        super.right( row, column );
    }

    @Override
    protected void wrong( int row, int column )
    {
        // TODO Auto-generated method stub
        super.right( row, column );
    }

}
