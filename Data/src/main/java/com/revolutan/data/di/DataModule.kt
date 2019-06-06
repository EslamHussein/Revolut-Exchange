package com.revolutan.data.di

import com.revolutan.data.ExchangeRateDataRepository
import com.revolutan.data.mapper.ExchangeRateMapper
import com.revolutan.data.store.ExchangeRateDataStoreFactory
import com.revolutan.data.store.ExchangeRateRemoteDataStore
import com.revolutan.domain.repository.ExchangeRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    single { ExchangeRateMapper() }
    single { ExchangeRateDataRepository(get(), get()) } bind ExchangeRepository::class
    single { ExchangeRateDataStoreFactory(get()) }
    single { ExchangeRateRemoteDataStore(get()) }
}