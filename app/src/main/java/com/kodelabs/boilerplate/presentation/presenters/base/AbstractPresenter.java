package com.kodelabs.boilerplate.presentation.presenters.base;


import com.kodelabs.boilerplate.domain.executor.ThreadExecutor;
import com.kodelabs.boilerplate.domain.executor.MainThread;

/**
 * This is a base class for all presenters which are communicating with interactors. This base class will hold a
 * reference to the ThreadExecutor and MainThread objects that are needed for running interactors in a background thread.
 */
public abstract class AbstractPresenter {
    protected ThreadExecutor mThreadExecutor;
    protected MainThread mMainThread;

    public AbstractPresenter(ThreadExecutor threadExecutor, MainThread mainThread) {
        mThreadExecutor = threadExecutor;
        mMainThread = mainThread;
    }
}
