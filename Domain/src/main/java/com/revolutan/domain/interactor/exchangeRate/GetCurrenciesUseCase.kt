package com.revolutan.domain.interactor.exchangeRate

import com.revolutan.domain.executer.ExecutionThread
import com.revolutan.domain.interactor.SingleUseCase
import com.revolutan.domain.model.Currency
import io.reactivex.Single

class GetCurrenciesUseCase(executionThread: ExecutionThread) :
    SingleUseCase<List<Currency>, GetCurrenciesUseCase.Params>(executionThread) {
    override fun buildUseCaseSingle(params: Params?): Single<List<Currency>> {
        if (params == null) throw IllegalArgumentException("Params can't be null")

        return Single.just(params).map {
            val currencies: MutableList<Currency> = mutableListOf()
            currencies.add(Currency(it.base))
            currencies.addAll(params.rates.toList().map { rate ->
                Currency(rate.first)
            })
            return@map currencies
        }
    }

    data class Params(val base: String, val rates: Map<String, Double>) {
        companion object {
            fun createFromCurrencies(base: String, rates: Map<String, Double>): Params {
                return Params(base, rates)
            }
        }
    }
}