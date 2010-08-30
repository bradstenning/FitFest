package org.fitfest.core;

import javax.swing.JComboBox;

import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JComboBoxFixture;

public abstract class ComboBoxAbstractCommandProcessor extends AbstractCommandProcessor<JComboBoxFixture>
{

    public ComboBoxAbstractCommandProcessor()
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