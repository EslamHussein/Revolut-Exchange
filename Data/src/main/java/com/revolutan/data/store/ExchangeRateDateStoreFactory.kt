package com.revolutan.data.store

import com.revolutan.data.repository.ExchangeRateDataStore

class ExchangeRateDateStoreFactory(private val exchangeRateRemoteDataStore: ExchangeRateRemoteDataStore) {

    open fun getDataStore(): ExchangeRateDataStore {
        return exchangeRateRemoteDataStore
    }
}