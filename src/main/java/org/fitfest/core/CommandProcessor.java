package org.fitfest.core;

import org.fest.swing.fixture.FrameFixture;
/**
 * Implement this interface so you can be called
 * by the {@link FitfestFixture}.
 * 
 * A command processor is designed to be used on table in the
 * following format:
 * |command|what ever| info| your command wants|
 *
 */
public interface CommandProcessor
{
    /**
     * This is what the {@link FitfestFixture} tests to see
     * if your class can handle the row in the table.
     * If the first column in the row matches the string returned from this
     * function handle row will be called next.
     * @return The name of the command that can be processed.
     */
    String getCommandString();

    /**
     * Handle the row of which your command was called.
     * @param window FrameFixture used to look up components
     * @param rowHandler used to call right or wrong based on your results
     */
    void handleRow( FrameFixture window, RowHandler rowHandler );
}
