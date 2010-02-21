package org.fitfest.core;

import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JTextComponentFixture;

public class CheckTextCommandProcessor implements CommandProcessor {

	@Override
	public String getCommandString() {
		return "checkText";
	}

	@Override
	public void handleRow(FrameFixture window, RowHandler rowHandler) {
		JTextComponentFixture textBox = window.textBox(rowHandler.getText(1));
		try
		{
			textBox.requireText(rowHandler.getText(2));
		}
		catch (AssertionError e) 
		{
			rowHandler.wrong(2, textBox.text());
		}
		rowHandler.right(2);

	}

}
