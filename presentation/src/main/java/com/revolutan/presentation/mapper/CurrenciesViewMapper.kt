package com.revolutan.presentation.mapper

import com.revolutan.domain.model.Currency
import com.revolutan.presentation.model.CurrencyView

class CurrenciesViewMapper : Mapper<CurrencyView, Currency> {

    override fun mapToView(type: Currency): CurrencyView {
        return CurrencyView(type.currency, type.value)
    }

    override fun mapFromView(view: CurrencyView): Currency {
        return Currency(view.currency, view.value)
    }

}