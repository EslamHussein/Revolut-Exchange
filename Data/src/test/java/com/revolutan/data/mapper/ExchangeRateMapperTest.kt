package com.revolutan.data.mapper

import com.revolutan.data.test.ExchangeRateDataFactory
import org.junit.Assert
import org.junit.Test

class ExchangeRateMapperTest {
    private val mapper = ExchangeRateMapper()
    @Test
    fun mapFromEntity() {

        val entity = ExchangeRateDataFactory.exchangeRateEntity()
        val model = mapper.mapFromEntity(entity)

        Assert.assertEquals(entity.base, model.base)
        Assert.assertEquals(entity.date, model.date)
        Assert.assertEquals(entity.rates, model.rates)


    }

    @Test
    fun mapToEntity() {

        val model = ExchangeRateDataFactory.exchangeRate()
        val entity = mapper.mapToEntity(model)

        Assert.assertEquals(entity.base, model.base)
        Assert.assertEquals(entity.date, model.date)
        Assert.assertEquals(entity.rates, model.rates)

    }
}