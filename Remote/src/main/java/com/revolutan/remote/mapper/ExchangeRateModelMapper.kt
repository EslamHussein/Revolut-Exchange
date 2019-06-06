package com.revolutan.remote.mapper

import com.revolutan.data.model.ExchangeRateEntity
import com.revolutan.remote.model.ExchangeRateModel

class ExchangeRateModelMapper : ModelMapper<ExchangeRateModel, ExchangeRateEntity> {
    override fun mapFromModel(model: ExchangeRateModel): ExchangeRateEntity {
        return ExchangeRateEntity(model.base, model.date, model.rates)
    }
}