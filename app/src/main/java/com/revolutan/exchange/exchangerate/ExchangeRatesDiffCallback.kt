package com.revolutan.exchange.exchangerate

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.revolutan.presentation.model.CurrencyView

class ExchangeRatesDiffCallback(
    private val newCurrencies: List<CurrencyView>,
    private val oldCurrencies: List<CurrencyView>
) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldCurrencies.size
    }

    override fun getNewListSize(): Int {
        return newCurrencies.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCurrencies[oldItemPosition].currency === newCurrencies[newItemPosition].currency
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCurrencies[oldItemPosition] == newCurrencies[newItemPosition]
    }
}