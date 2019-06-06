package com.revolutan.domain.di

import com.revolutan.domain.interactor.exchangeRate.GetCurrenciesUseCase
import com.revolutan.domain.interactor.exchangeRate.GetExchangeRateUseCase
import com.revolutan.domain.interactor.exchangeRate.SortCurrenciesUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetExchangeRateUseCase(get(), get()) }
    factory { GetCurrenciesUseCase(get()) }
    factory { SortCurrenciesUseCase() }
}