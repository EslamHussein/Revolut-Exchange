package com.revolutan.remote.mapper

import com.revolutan.test.ExchangeRateFakeDataFactory
import org.junit.Assert.assertEquals
import org.junit.Test

class ExchangeRateModelMapperTest {

    private val mapper = ExchangeRateModelMapper()

    @Test
    fun mapFromModel() {

        val model = ExchangeRateFakeDataFactory.exchangeRateModel()
        val entity = mapper.mapFromModel(model)

        assertEquals(model.base, entity.base)
        assertEquals(model.date, entity.date)
        assertEquals(model.rates, entity.rates)
    }
}