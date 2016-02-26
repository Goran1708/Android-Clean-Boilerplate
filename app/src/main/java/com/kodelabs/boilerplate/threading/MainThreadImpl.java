package com.kodelabs.boilerplate.threading;

import com.kodelabs.boilerplate.domain.executor.MainThread;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;


/**
 * This class makes sure that the runnable we provide will be run on the main UI thread.
 */
@Singleton
public class MainThreadImpl implements MainThread {

    @Inject
    public MainThreadImpl() {}

    @Override public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
