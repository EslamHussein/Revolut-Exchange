package com.revolutan.remote

import com.revolutan.data.model.ExchangeRateEntity
import com.revolutan.data.repository.ExchangeRateRemote
import com.revolutan.remote.mapper.ExchangeRateModelMapper
import com.revolutan.remote.service.ExchangeRateService
import io.reactivex.Observable

class ExchangeRateRemoteImpl(
    private val exchangeRateService: ExchangeRateService,
    private val exchangeRateModelMapper: ExchangeRateModelMapper
) : ExchangeRateRemote {
    override fun getExchangeRate(base: String): Observable<ExchangeRateEntity> {
        return exchangeRateService.getExchangeRate(base).map { exchangeRateModelMapper.mapFromModel(it) }
    }
}