package com.revolutan.domain.executer

import io.reactivex.Scheduler


interface ExecutionThread {
    val subscribeScheduler: Scheduler
    val observerScheduler: Scheduler
}