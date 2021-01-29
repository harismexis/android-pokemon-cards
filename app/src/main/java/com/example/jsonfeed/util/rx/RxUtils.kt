package com.example.jsonfeed.util.rx

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.SingleTransformer

fun <T> setSchedulersSingle(provider: BaseSchedulerProvider): SingleTransformer<T, T> {
    return SingleTransformer { upstream: Single<T> ->
        upstream
            .subscribeOn(provider.io())
            .observeOn(provider.ui())
    }
}

fun <T> setSchedulersObservable(provider: BaseSchedulerProvider): ObservableTransformer<T, T> {
    return ObservableTransformer { observable: Observable<T> ->
        observable
            .subscribeOn(provider.io())
            .observeOn(provider.ui())
    }
}