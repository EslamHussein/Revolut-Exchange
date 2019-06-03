package com.revolutan.exchange

import com.revolutan.domain.executer.ExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UIThread : ExecutionThread {
    override val subscribeScheduler: Scheduler
        get() = Schedulers.io()
    override val observerScheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}