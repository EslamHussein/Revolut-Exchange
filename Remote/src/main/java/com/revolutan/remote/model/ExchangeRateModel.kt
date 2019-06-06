package com.revolutan.remote.model

import com.google.gson.annotations.SerializedName

class ExchangeRateModel(
    @SerializedName("base") val base: String,
    @SerializedName("date") val date: String,
    @SerializedName("rates") val rates: Map<String, Double>
)