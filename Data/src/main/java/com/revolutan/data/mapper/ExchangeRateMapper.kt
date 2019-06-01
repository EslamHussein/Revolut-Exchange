package com.revolutan.data.mapper

import com.revolutan.data.model.ExchangeRateEntity
import com.revolutan.domain.model.ExchangeRate

class ExchangeRateMapper : EntityMapper<ExchangeRateEntity, ExchangeRate> {
    override fun mapFromEntity(entity: ExchangeRateEntity): ExchangeRate {
        return ExchangeRate(entity.base, entity.date, entity.rates)
    }

    override fun mapFromData(data: ExchangeRate): ExchangeRateEntity {
        return ExchangeRateEntity(data.base, data.date, data.rates)
    }
}