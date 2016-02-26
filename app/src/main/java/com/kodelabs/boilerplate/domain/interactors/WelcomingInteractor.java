package com.kodelabs.boilerplate.domain.interactors;


import com.kodelabs.boilerplate.domain.interactors.base.Interactor;


public interface WelcomingInteractor extends Interactor {

    interface Callback {
        void onMessageRetrieved(String message);

        void onRetrievalFailed(String error);
    }

    /**
     * This is the main method that starts an interactor. It will make sure that the interactor operation is done on a
     * background thread.
     */
    void execute(final Callback callback);

    void unsubscribeObservable();
}
