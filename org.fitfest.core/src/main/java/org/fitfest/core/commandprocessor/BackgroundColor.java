package org.fitfest.core.commandprocessor;

import java.awt.Component;

import org.fest.swing.core.ComponentFinder;
import org.fest.swing.fixture.ComponentFixture;
import org.fest.swing.fixture.FrameFixture;
import org.fitfest.core.RowHandler;

public class BackgroundColor extends AbstractCommandProcessor<ComponentFixture<Component>>
{
    @Override
    public String getCommandString()
    {
        return "backgroundColor";
    }

    @Override
    public ComponentFixture<Component> findFixture( FrameFixture window, RowHandler rowHandler )
    {
        ComponentFinder finder = window.robot.finder();
        ComponentFixture<Component> componentFixture = 
            new ComponentFixture<Component>(window.robot, finder.findByName(window.component(), rowHandler.getText( 1 ) ))
        {
        };
        return componentFixture;
    }

    @Override
    public boolean evaluate( RowHandler rowHandler, ComponentFixture<Component> fixture )
    {
        fixture.background().requireEqualTo( rowHandler.getText( 2 ) );
        return true;
    }

    @Override
    public String actual( ComponentFixture<Component> fixture )
    {
        return fixture.background().target().toString();
    }
}
