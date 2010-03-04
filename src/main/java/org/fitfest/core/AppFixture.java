package org.fitfest.core;

import org.fest.swing.launcher.ApplicationLauncher;

import fitnesse.fixtures.TableFixture;

public class AppFixture extends TableFixture
{

    @Override
    protected void doStaticTable( final int rows )
    {
        ApplicationLauncher.application( args[0] ).start();
    }

}
