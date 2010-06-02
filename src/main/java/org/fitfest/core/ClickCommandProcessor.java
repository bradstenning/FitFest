package org.fitfest.core;

import javax.swing.JButton;

import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.fixture.FrameFixture;

public class ClickCommandProcessor implements CommandProcessor
{
    public String getCommandString()
    {
        return "click";
    }

    public void handleRow( final FrameFixture window, final RowHandler rowHandler )
    {
        window.button( new GenericTypeMatcher<JButton>(JButton.class) {
            private boolean firstCome = true;
            
            @Override
            protected boolean isMatching( JButton component )
            {
                if(firstCome && rowHandler.getText( 1 ).equals( component.getName() ) && component.isShowing()) {
                    firstCome = false;
                    return true;
                }
                return false;
            }
            
        }).click();
        rowHandler.right( 1 );
    }
}
