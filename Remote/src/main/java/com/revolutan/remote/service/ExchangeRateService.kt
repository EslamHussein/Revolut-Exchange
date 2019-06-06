package com.revolutan.remote.service

import com.revolutan.remote.model.ExchangeRateModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRateService {
    @GET("latest")
    fun getExchangeRate(@Query("base") base: String): Observable<ExchangeRateModel>
}