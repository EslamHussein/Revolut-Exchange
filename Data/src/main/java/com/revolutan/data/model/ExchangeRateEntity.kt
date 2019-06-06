package com.revolutan.data.model

data class ExchangeRateEntity(val base: String, val date: String, val rates: Map<String, Double>)