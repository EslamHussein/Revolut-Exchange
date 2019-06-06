package com.revolutan.test

import com.revolutan.data.model.ExchangeRateEntity
import com.revolutan.remote.model.ExchangeRateModel
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import kotlin.collections.HashMap

object ExchangeRateFakeDataFactory {

    private fun randomString() = UUID.randomUUID().toString()

    private fun randomDouble(): Double {
        return ThreadLocalRandom.current().nextDouble(0.0, 100.0 + 1)
    }

    fun exchangeRateModel(): ExchangeRateModel {
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
        return ExchangeRateModel(base = randomString(), date = randomString(), rates = rates)

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