package com.revolutan.domain.interactor

import com.revolutan.domain.executer.ExecutionThread
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver

abstract class SingleUseCase<T, in Params> constructor(private val execution: ExecutionThread) {

    private val disposables = CompositeDisposable()
    abstract fun buildUseCaseSingle(params: Params? = null): Single<T>

    open fun execute(observer: DisposableSingleObserver<T>, params: Params? = null) {

        val single = this.buildUseCaseSingle(params = params)
            .subscribeOn(execution.subscribeScheduler)
            .observeOn(execution.observerScheduler)
        addDisposable(single.subscribeWith(observer))
    }

    fun dispose() {
        disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}