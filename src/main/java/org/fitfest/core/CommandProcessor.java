package org.fitfest.core;

import org.fest.swing.fixture.FrameFixture;

public interface CommandProcessor
{
    String getCommandString();

    void handleRow( FrameFixture window, RowHandler rowHandler );
}
