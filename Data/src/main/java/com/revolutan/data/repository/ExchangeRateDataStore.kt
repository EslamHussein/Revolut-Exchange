package com.revolutan.data.repository

import com.revolutan.data.model.ExchangeRateEntity
import io.reactivex.Observable

interface ExchangeRateDataStore {
    fun getExchangeRate(base: String): Observable<ExchangeRateEntity>
}