package com.kodelabs.boilerplate.domain.interactors.impl;

import com.kodelabs.boilerplate.domain.executor.ThreadExecutor;
import com.kodelabs.boilerplate.domain.executor.MainThread;
import com.kodelabs.boilerplate.domain.interactors.DefaultSubscriber;
import com.kodelabs.boilerplate.domain.interactors.WelcomingInteractor;
import com.kodelabs.boilerplate.domain.interactors.base.AbstractInteractor;
import com.kodelabs.boilerplate.domain.repository.MessageRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class WelcomingInteractorImpl extends AbstractInteractor implements WelcomingInteractor {

    private WelcomingInteractor.Callback mCallback;
    private MessageRepository            mMessageRepository;

    @Inject
    public WelcomingInteractorImpl(ThreadExecutor threadThreadExecutor,
                                   MainThread mainThread,
                                    MessageRepository messageRepository) {
        super(threadThreadExecutor, mainThread);
        mMessageRepository = messageRepository;
    }

    @Override
    public void execute(Callback callback) {
        mCallback = callback;

        //execute from base
        execute(new StringSubscriber());
    }

    @Override
    public void unsubscribeObservable() {
        unsubscribe();
    }

    @Override public Observable buildUseCaseObservable() {
        return this.mMessageRepository.getWelcomeMessage();
    }

    private void notifyError() {
        mCallback.onRetrievalFailed("Nothing to welcome you with :(");
    }

    private void postMessage(final String msg) {
        mCallback.onMessageRetrieved(msg);
    }

    private final class StringSubscriber extends DefaultSubscriber<String> {

        @Override public void onCompleted() {
        }

        @Override public void onError(Throwable e) {
            WelcomingInteractorImpl.this.notifyError();
        }

        @Override public void onNext(String message) {
            WelcomingInteractorImpl.this.postMessage(message);
        }
    }
}
