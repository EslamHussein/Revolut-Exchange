package com.revolutan.presentation.di

import com.revolutan.domain.interactor.exchangeRate.GetExchangeRateUseCase
import com.revolutan.presentation.ExchangeRateViewModel
import com.revolutan.presentation.mapper.ExchangeRateViewMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.mockito.Mockito

val presentationModuleTesting = module {
    viewModel { ExchangeRateViewModel(get(), get(), get(), get()) }
    single { Mockito.mock(GetExchangeRateUseCase::class.java) }
    single { Mockito.mock(ExchangeRateViewMapper::class.java) }
}