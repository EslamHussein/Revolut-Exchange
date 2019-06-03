package com.revolutan.presentation.mapper

import com.revolutan.presentation.test.ExchangeRateFakeDataFactory
import org.junit.Assert
import org.junit.Test

class ExchangeRateViewMapperTest {

    private val mapper = ExchangeRateViewMapper()

    @Test
    fun testMapToView() {
        val data = ExchangeRateFakeDataFactory.getExchangeRate()
        val dataView = mapper.mapToView(data)

        Assert.assertEquals(data.base, dataView.base)
        Assert.assertEquals(data.date, dataView.date)
        Assert.assertEquals(data.rates, dataView.rates)
    }
}