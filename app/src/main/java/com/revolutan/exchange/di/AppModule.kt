package com.revolutan.exchange.di

import com.revolutan.domain.executer.ExecutionThread
import com.revolutan.exchange.UIThread
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    factory { UIThread() } bind ExecutionThread::class
}