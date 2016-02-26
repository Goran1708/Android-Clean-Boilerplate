package com.kodelabs.boilerplate.domain.interactors.base;

import com.kodelabs.boilerplate.domain.executor.ThreadExecutor;
import com.kodelabs.boilerplate.domain.executor.MainThread;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by dmilicic on 8/4/15.
 * <p/>
 * This abstract class implements some common methods for all interactors. Cancelling an interactor, check if its running
 * and finishing an interactor has mostly the same code throughout so that is why this class was created. Field methods
 * are declared volatile as we might use these methods from different threads (mainly from UI).
 * <p/>
 * For example, when an activity is getting destroyed then we should probably cancel an interactor
 * but the request will come from the UI thread unless the request was specifically assigned to a background thread.
 */
public abstract class AbstractInteractor implements Interactor {

    protected ThreadExecutor mThreadThreadExecutor;
    protected MainThread mMainThread;

    private Subscription subscription = Subscriptions.empty();

    protected volatile boolean mIsCanceled;
    protected volatile boolean mIsRunning;

    public AbstractInteractor(ThreadExecutor threadThreadExecutor, MainThread mainThread) {
        mThreadThreadExecutor = threadThreadExecutor;
        mMainThread = mainThread;
    }

    public void cancel() {
        mIsCanceled = true;
        mIsRunning = false;
    }

    public boolean isRunning() {
        return mIsRunning;
    }

    public void onFinished() {
        mIsRunning = false;
        mIsCanceled = false;
    }

    /**
     * Builds an {@link rx.Observable} which will be used when executing the current.
     */
    protected abstract Observable buildUseCaseObservable();

    /**
     * Executes the current use case.
     *
     * @param UseCaseSubscriber The guy who will be listen to the observable build with {@link #buildUseCaseObservable()}.
     */
    @SuppressWarnings("unchecked")
    public void execute(Subscriber UseCaseSubscriber) {

        this.mIsRunning = true;

        this.subscription = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.from(mThreadThreadExecutor))
                .observeOn(mMainThread.getScheduler())
                .subscribe(UseCaseSubscriber);
    }

    /**
     * Unsubscribes from current {@link rx.Subscription}.
     */
    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
