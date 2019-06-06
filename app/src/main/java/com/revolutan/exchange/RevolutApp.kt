package com.revolutan.exchange

import android.app.Application
import com.revolutan.data.di.dataModule
import com.revolutan.domain.di.domainModule
import com.revolutan.exchange.di.appModule
import com.revolutan.presentation.di.presentationModule
import com.revolutan.remote.di.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RevolutApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RevolutApp)
            androidLogger()
            modules(listOf(appModule, domainModule, dataModule, remoteModule, presentationModule))
        }
    }
}