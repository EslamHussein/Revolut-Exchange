package com.revolutan.presentation.model

data class ExchangeRateView(
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)