package com.revolutan.data.store

import com.revolutan.data.model.ExchangeRateEntity
import com.revolutan.data.repository.ExchangeRateDataStore
import com.revolutan.data.repository.ExchangeRateRemote
import io.reactivex.Observable

class ExchangeRateRemoteDataStore(private val exchangeRateRemote: ExchangeRateRemote) : ExchangeRateDataStore {
    override fun getExchangeRate(base: String): Observable<ExchangeRateEntity> {
        return exchangeRateRemote.getExchangeRate(base)
    }
}