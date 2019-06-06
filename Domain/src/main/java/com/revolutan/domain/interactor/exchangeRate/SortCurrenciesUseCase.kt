package com.revolutan.domain.interactor.exchangeRate

import com.revolutan.domain.interactor.SynchronousUseCase
import com.revolutan.domain.model.Currency

class SortCurrenciesUseCase :
    SynchronousUseCase<List<Currency>, SortCurrenciesUseCase.Params> {
    override fun execute(params: Params?): List<Currency> {
        if (params == null) throw IllegalArgumentException("Params can't be null")

        return mutableListOf<Currency>().apply {
            add(Currency(params.selectedBase.currency))
            addAll(params.rates.toList().map {
                Currency(it.first)
            })
        }
    }


    data class Params(
        val selectedBase: Currency,
        val rates: Map<String, Double>
    ) {
        companion object {
            fun fromSortCurrencies(
                selectedBase: Currency,
                rates: Map<String, Double>
            ): Params {
                return Params(selectedBase, rates)
            }
        }
    }
}





