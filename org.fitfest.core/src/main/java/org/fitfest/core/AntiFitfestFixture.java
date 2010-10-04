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
        super.append( row, column, " <span class=\"fit_label\">expected</span>");
        super.append( row, column, "<hr>");
        super.append( row, column, actual);
        super.append( row, column, " <span class=\"fit_label\">actual</span>" );
    }

    @Override
    protected void wrong( int row, int column )
    {
        super.right( row, column );
    }

}
