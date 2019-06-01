package com.revolutan.data

import com.revolutan.data.mapper.ExchangeRateMapper
import com.revolutan.data.store.ExchangeRateDateStoreFactory
import com.revolutan.domain.interactor.exchangeRate.GetExchangeRateUseCase
import com.revolutan.domain.model.ExchangeRate
import com.revolutan.domain.repository.ExchangeRepository
import io.reactivex.Observable

class ExchangeRateDataRepository(
    private val mapper: ExchangeRateMapper,
    private val exchangeRateDateStoreFactory: ExchangeRateDateStoreFactory
) :
    ExchangeRepository {
    override fun getExchangeRate(params: GetExchangeRateUseCase.Params): Observable<ExchangeRate> {
        return exchangeRateDateStoreFactory.getDataStore()
            .getExchangeRate(params.base).map { mapper.mapFromEntity(it) }
    }
}