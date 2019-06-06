package com.revolutan.domain.model

data class ExchangeRate(val base: String, val date: String, val rates: Map<String, Double>)
data class Exchange(val exchangeRate: ExchangeRate, val exchanges: List<Currency>)