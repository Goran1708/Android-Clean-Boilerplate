package com.kodelabs.boilerplate.storage;

import com.kodelabs.boilerplate.domain.repository.MessageRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by dmilicic on 1/29/16.
 */
public class WelcomeMessageRepository implements MessageRepository {

    @Inject
    public WelcomeMessageRepository() {}

    @Override
    public Observable<String> getWelcomeMessage() {

        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String msg = "Welcome, friend!"; // let's be friendly
                try {
                    if(msg != null) {
                        // let's simulate some network/database lag
                        Thread.sleep(2000);
                        subscriber.onNext(msg);
                        subscriber.onCompleted();
                    } else {
                        subscriber.onError(new Exception());
                    }
                } catch (InterruptedException e) {
                    subscriber.onError(new Exception(e.getCause()));
                }

            }
        });
    }
}
