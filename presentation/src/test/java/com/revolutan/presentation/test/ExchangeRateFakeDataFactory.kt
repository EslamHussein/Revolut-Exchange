package com.revolutan.presentation.test

import com.revolutan.domain.model.ExchangeRate
import com.revolutan.presentation.model.ExchangeRateView
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import kotlin.collections.HashMap

object ExchangeRateFakeDataFactory {


    private fun randomString() = UUID.randomUUID().toString()
    fun randomDouble(): Double {
        return ThreadLocalRandom.current().nextDouble(0.0, 100.0 + 1)
    }

    fun getExchangeRate(): ExchangeRate {
        val rates = HashMap<String, Double>()
        rates[randomString()] = randomDouble()
        rates[randomString()] = randomDouble()
        rates[randomString()] = randomDouble()
        rates[randomString()] = randomDouble()
        rates[randomString()] = randomDouble()
        rates[randomString()] = randomDouble()
        rates[randomString()] = randomDouble()
        rates[randomString()] = randomDouble()
        rates[randomString()] = randomDouble()
        rates[randomString()] = randomDouble()
        rates[randomString()] = randomDouble()
        rates[randomString()] = randomDouble()
        return ExchangeRate(base = randomString(), date = randomString(), rates = rates)
    }

    fun getExchangeRateView(): ExchangeRateView {
        val rates = HashMap<String, Double>()
        rates[randomString()] = randomDouble()
        rates[randomString()] = randomDouble()
        rates[randomString()] = randomDouble()
        rates[randomString()] = randomDouble()
        rates[randomString()] = randomDouble()
        rates[randomString()] = randomDouble()
        rates[randomString()] = randomDouble()
        rates[randomString()] = randomDouble()
        rates[randomString()] = randomDouble()
        rates[randomString()] = randomDouble()
        rates[randomString()] = randomDouble()
        rates[randomString()] = randomDouble()
        return ExchangeRateView(base = randomString(), date = randomString(), rates = rates)
    }
}