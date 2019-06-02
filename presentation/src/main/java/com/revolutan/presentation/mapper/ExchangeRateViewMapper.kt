package com.revolutan.presentation.mapper

import com.revolutan.domain.model.ExchangeRate
import com.revolutan.presentation.model.ExchangeRateView

class ExchangeRateViewMapper : Mapper<ExchangeRateView, ExchangeRate> {
    override fun mapToView(type: ExchangeRate): ExchangeRateView {
        return ExchangeRateView(type.base, type.date, type.rates)
    }
}