package com.revolutan.presentation.di

import com.revolutan.presentation.ExchangeRateViewModel
import com.revolutan.presentation.error.DefaultErrorHandler
import com.revolutan.presentation.error.ErrorHandler
import com.revolutan.presentation.mapper.CurrenciesViewMapper
import com.revolutan.presentation.resource.AppResources
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val presentationModule = module {
    viewModel { ExchangeRateViewModel(get(), get(), get(), get()) }
    factory { CurrenciesViewMapper() }
    single { AppResources(androidContext()) }
    factory { DefaultErrorHandler(get()) } bind ErrorHandler::class
}