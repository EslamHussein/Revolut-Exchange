package com.revolutan.data.store

import com.revolutan.data.repository.ExchangeRateDataStore

open class ExchangeRateDataStoreFactory(private val exchangeRateRemoteDataStore: ExchangeRateRemoteDataStore) {

    open fun getDataStore(): ExchangeRateDataStore {
        return exchangeRateRemoteDataStore
    }
}