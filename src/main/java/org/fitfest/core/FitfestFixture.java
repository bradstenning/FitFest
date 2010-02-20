package org.fitfest.core;

import java.awt.Frame;
import java.util.Collections;
import java.util.Map;

import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.core.Robot;
import org.fest.swing.finder.WindowFinder;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.launcher.ApplicationLauncher;

import fitnesse.fixtures.TableFixture;

public class FitfestFixture extends TableFixture {
	private FrameFixture window = null;
	private Map<String, ClickCommandProcessor> commandHandlers = Collections.singletonMap("click", new ClickCommandProcessor());
	private Robot m_robot;
	
	protected void doStaticTable(int rows) {
		try
		{
			startapp();

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
		finally
		{
			tearDown();
		}
	}

	private void startapp() {
		 m_robot = BasicRobot.robotWithNewAwtHierarchy();

		 ApplicationLauncher.application( args[0] ).start();
		 window = WindowFinder.findFrame(new GenericTypeMatcher<Frame>(Frame.class) 
				 {
			 protected boolean isMatching(Frame frame) 
			 {
				 return args[1].equals(frame.getTitle()) && frame.isShowing();
			 }

				 }
		 ).using(m_robot);
	}
	
	private void tearDown() 
    {
            if(m_robot != null)
                    m_robot.cleanUp();
            
    }

}
