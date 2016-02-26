package com.kodelabs.boilerplate.dagger_di.modules;

import com.kodelabs.boilerplate.domain.executor.ThreadExecutor;
import com.kodelabs.boilerplate.domain.executor.MainThread;
import com.kodelabs.boilerplate.domain.executor.impl.JobThreadExecutor;
import com.kodelabs.boilerplate.threading.MainThreadImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by GoranCol on 2/23/2016.
 */
@Module
public class ExecutorModule {

    @Provides
    @Singleton
    public ThreadExecutor provideExecutor(JobThreadExecutor threadExecutor) {
        return threadExecutor;
    }

    @Provides
    @Singleton
    public MainThread provideMainThread(MainThreadImpl mainThread) {
        return mainThread;
    }

}
