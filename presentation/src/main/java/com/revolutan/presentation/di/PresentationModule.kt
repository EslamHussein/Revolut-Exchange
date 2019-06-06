package com.revolutan.presentation.di

import com.revolutan.presentation.ExchangeRateViewModel
import com.revolutan.presentation.error.DefaultErrorHandler
import com.revolutan.presentation.error.ErrorHandler
import com.revolutan.presentation.mapper.CurrenciesViewMapper
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val presentationModule = module {
    viewModel { ExchangeRateViewModel(get(), get(), get(), get()) }
    factory { CurrenciesViewMapper() }
    factory { DefaultErrorHandler(androidContext().resources) } bind ErrorHandler::class
}