package com.kodelabs.boilerplate.dagger_di.components;

import com.kodelabs.boilerplate.dagger_di.modules.ExecutorModule;
import com.kodelabs.boilerplate.dagger_di.modules.MainActivityModule;
import com.kodelabs.boilerplate.presentation.ui.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by GoranCol on 2/23/2016.
 */
@Singleton
@Component(modules = {
        ExecutorModule.class,
        MainActivityModule.class
})

public interface MainActivityComponent {

    void inject(MainActivity activity);
}
