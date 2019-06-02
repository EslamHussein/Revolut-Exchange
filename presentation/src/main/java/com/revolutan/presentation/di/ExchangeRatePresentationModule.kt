package com.revolutan.presentation.di

import com.revolutan.presentation.ExchangeRateViewModel
import com.revolutan.presentation.mapper.ExchangeRateViewMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ExchangeRatePresentationModule = module {
    viewModel { ExchangeRateViewModel(get(), get()) }
    factory { ExchangeRateViewMapper() }
}