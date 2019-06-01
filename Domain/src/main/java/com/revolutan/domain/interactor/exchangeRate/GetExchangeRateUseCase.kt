package com.revolutan.domain.interactor.exchangeRate

import com.revolutan.domain.executer.ExecutionThread
import com.revolutan.domain.interactor.ObservableUseCase
import com.revolutan.domain.model.ExchangeRate
import com.revolutan.domain.repository.ExchangeRepository
import io.reactivex.Observable


class GetExchangeRateUseCase(
    private val exchangeRepository: ExchangeRepository,
    executionThread: ExecutionThread
) : ObservableUseCase<ExchangeRate,
        GetExchangeRateUseCase.Params>(executionThread) {
    override fun buildUseCaseObservable(params: Params?): Observable<ExchangeRate> {
        if (params == null) throw IllegalArgumentException("Params can't be null")
        return exchangeRepository.getExchangeRate(params)
    }

    data class Params(val base: String) {
        companion object {
            fun fromExchangeRate(base: String): Params = Params(base)
        }
    }
}