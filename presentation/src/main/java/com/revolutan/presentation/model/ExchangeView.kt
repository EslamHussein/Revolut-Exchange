package com.revolutan.presentation.model

data class ExchangeView(val currencies: List<CurrencyView>, val rates: Map<String, Double>)