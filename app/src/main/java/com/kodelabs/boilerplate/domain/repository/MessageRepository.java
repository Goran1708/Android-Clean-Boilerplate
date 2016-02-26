package com.kodelabs.boilerplate.domain.repository;

import rx.Observable;
/**
 * A repository that is responsible for getting our welcome message.
 */
public interface MessageRepository {

    Observable<String> getWelcomeMessage();
}
