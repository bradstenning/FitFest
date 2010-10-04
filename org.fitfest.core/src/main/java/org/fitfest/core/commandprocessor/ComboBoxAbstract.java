package org.fitfest.core.commandprocessor;

import javax.swing.JComboBox;

import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JComboBoxFixture;
import org.fitfest.core.RowHandler;

public abstract class ComboBoxAbstract extends AbstractCommandProcessor<JComboBoxFixture>
{

    public ComboBoxAbstract()
    {
        super();
    }

    @Override
    public JComboBoxFixture findFixture( FrameFixture window, RowHandler rowHandler )
    {
        return window.comboBox( rowHandler.getText( 1 ) );
    }

    @Override
    public String actual( JComboBoxFixture fixture )
    {
        return fixture.targetCastedTo( JComboBox.class ).getSelectedItem().toString();
    }

}