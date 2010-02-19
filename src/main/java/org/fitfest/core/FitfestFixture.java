package org.fitfest.core;

import java.util.Collections;
import java.util.Map;

import org.fest.swing.fixture.FrameFixture;

import fitnesse.fixtures.TableFixture;

public class FitfestFixture extends TableFixture {
	private FrameFixture window = null;
	private Map<String, ClickCommandProcessor> commandHandlers = Collections.singletonMap("click", new ClickCommandProcessor());
	
	protected void doStaticTable(int rows) {
		for(int i = 0; i < rows; i++) {
			final int row = i;
			RowHandler rowHandler = new RowHandler() {
				public String getText(int column) {
					return FitfestFixture.this.getText(row, column);
				}
				
				public void right(int column) {
					FitfestFixture.this.right(row, column);
				}

				public void wrong(int column) {
					FitfestFixture.this.wrong(row, column);
				}

				public void wrong(int column, String actual) {
					FitfestFixture.this.wrong(row, column, actual);
				}
			};
			
			commandHandlers.get(getText(row, 0)).handleRow(window, rowHandler);
		}
	}
}
