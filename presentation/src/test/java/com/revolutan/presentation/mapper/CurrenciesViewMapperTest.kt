package com.revolutan.presentation.mapper

import com.revolutan.presentation.test.ExchangeRateFakeDataFactory
import org.junit.Assert
import org.junit.Test

class CurrenciesViewMapperTest {
    private val mapper = CurrenciesViewMapper()

    @Test
    fun mapToView() {

        val data = ExchangeRateFakeDataFactory.getCurrency()
        val dataView = mapper.mapToView(data)

        Assert.assertEquals(data.currency, dataView.currency)
        Assert.assertEquals(data.value, dataView.value)

    }
}