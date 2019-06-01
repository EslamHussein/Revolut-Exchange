package com.revolutan.domain.repository

import com.revolutan.domain.interactor.exchangeRate.GetExchangeRateUseCase
import com.revolutan.domain.model.ExchangeRate
import io.reactivex.Observable

interface ExchangeRepository {
    fun getExchangeRate(params: GetExchangeRateUseCase.Params): Observable<ExchangeRate>
}