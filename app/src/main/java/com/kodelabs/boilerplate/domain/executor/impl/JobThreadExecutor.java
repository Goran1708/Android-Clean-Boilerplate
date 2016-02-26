package com.kodelabs.boilerplate.domain.executor.impl;

import com.kodelabs.boilerplate.domain.executor.ThreadExecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * This singleton class will make sure that each interactor operation gets a background thread.
 * <p/>
 */
@Singleton
public class JobThreadExecutor implements ThreadExecutor {

    private static final int                     CORE_POOL_SIZE  = 3;
    private static final int                     MAX_POOL_SIZE   = 5;
    private static final int                     KEEP_ALIVE_TIME = 120;
    private static final TimeUnit                TIME_UNIT       = TimeUnit.SECONDS;
    private static final BlockingQueue<Runnable> WORK_QUEUE      = new LinkedBlockingQueue<Runnable>();

    private ThreadPoolExecutor mThreadPoolExecutor;

    @Inject
    public JobThreadExecutor() {
        long keepAlive = KEEP_ALIVE_TIME;
        mThreadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                keepAlive,
                TIME_UNIT,
                WORK_QUEUE);
    }

    @Override public void execute(Runnable runnable) {
        if (runnable == null) {
            throw new IllegalArgumentException("Runnable to execute cannot be null");
        }
        this.mThreadPoolExecutor.execute(runnable);
    }
}
