package com.revolutan.data.repository

import com.revolutan.data.model.ExchangeRateEntity
import io.reactivex.Observable


interface ExchangeRateRemote {
    fun getExchangeRate(base:String):Observable<ExchangeRateEntity>
}