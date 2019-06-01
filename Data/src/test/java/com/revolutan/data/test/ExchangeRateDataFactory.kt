package com.revolutan.data.test

import com.revolutan.data.model.ExchangeRateEntity
import com.revolutan.domain.model.ExchangeRate
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import kotlin.collections.HashMap

object ExchangeRateDataFactory {


    private fun randomString() = UUID.randomUUID().toString()
    fun randomDouble(): Double {
        return ThreadLocalRandom.current().nextDouble(0.0, 100.0 + 1)
    }

    fun exchangeRate(): ExchangeRate {
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

    fun exchangeRateEntity(): ExchangeRateEntity {
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
        return ExchangeRateEntity(base = randomString(), date = randomString(), rates = rates)

    }
}