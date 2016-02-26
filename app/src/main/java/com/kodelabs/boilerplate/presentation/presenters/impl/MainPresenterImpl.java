package com.kodelabs.boilerplate.presentation.presenters.impl;

import com.kodelabs.boilerplate.domain.interactors.WelcomingInteractor;
import com.kodelabs.boilerplate.presentation.presenters.MainPresenter;

import javax.inject.Inject;

/**
 * Created by dmilicic on 12/13/15.
 */
public class MainPresenterImpl implements MainPresenter,
        WelcomingInteractor.Callback {

    private MainPresenter.View mView;
    private WelcomingInteractor welcomingInteractor;

    @Inject
    public MainPresenterImpl(View view, WelcomingInteractor welcomingInteractor) {
        this.welcomingInteractor = welcomingInteractor;
        mView = view;
    }

    @Override
    public void resume() {

        mView.showProgress();

//      // run the interactor
        welcomingInteractor.execute(this);
    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {
        welcomingInteractor.unsubscribeObservable();
    }

    @Override
    public void onError(String message) {
        mView.showError(message);
    }

    @Override
    public void onMessageRetrieved(String message) {
        mView.hideProgress();
        mView.displayWelcomeMessage(message);
    }

    @Override
    public void onRetrievalFailed(String error) {
        mView.hideProgress();
        onError(error);
    }
}
