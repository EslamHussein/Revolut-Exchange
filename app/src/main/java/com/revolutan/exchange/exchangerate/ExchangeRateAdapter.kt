package com.revolutan.exchange.exchangerate

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.revolutan.exchange.R
import com.revolutan.presentation.model.CurrencyView
import kotlinx.android.synthetic.main.currency_item_view.view.*


class ExchangeRateAdapter(
    private var data: List<CurrencyView> = emptyList(),
    private var rates: Map<String, Double>? = null,
    private var onChangingListener: ExchangeRateOnChangingListener
) :
    RecyclerView.Adapter<ExchangeRateAdapter.ExchangeRateViewHolder>() {
    private var baseExchange: Double = 1.0
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeRateViewHolder {
        context = parent.context
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.currency_item_view, parent, false)
        return ExchangeRateViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.count()
    }

    override fun onBindViewHolder(holder: ExchangeRateViewHolder, position: Int) {

        data[holder.adapterPosition].apply {
            value = ((rates?.get(this.currency) ?: 1.0) * baseExchange)
        }.also {
            holder.currencyTextView.text = it.currency
            holder.currencyValueEditText.setText(context.getString(R.string.currency_value_formatter).format(it.value))
        }


        holder.currencyValueEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(exchange: Editable?) {

                if (position == 0 && exchange != null && exchange.toString().toDoubleOrNull() !=null) {
                    baseExchange = exchange.toString().toDouble()
                    data[0].apply {
                        value = baseExchange
                    }
                }
                onChangingListener.updating(false)

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(exchange: CharSequence?, start: Int, before: Int, count: Int) {

                onChangingListener.updating(true)

            }
        })
    }


    fun updateData(updatedList: List<CurrencyView>) {

        val diffResult = DiffUtil.calculateDiff(
            ExchangeRatesDiffCallback(
                data,
                updatedList
            )
        )
        this.data = updatedList
        diffResult.dispatchUpdatesTo(this)

    }

    fun updateRates(rates: Map<String, Double>) {
        this.rates = rates
        notifyDataSetChanged()
    }

    inner class ExchangeRateViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var currencyTextView: TextView = view.currencyTextView
        var currencyValueEditText: EditText = view.currencyValueEditText

        init {
            currencyValueEditText.setOnFocusChangeListener { view, hasFocus ->

                if (adapterPosition > 0) {
                    data[adapterPosition].also {
                        baseExchange = it.value
                        onChangingListener.onchange(it)
                    }
                }
                if (adapterPosition == 0) {
                    onChangingListener.updating(true)
                }
            }
        }

    }

    interface ExchangeRateOnChangingListener {
        fun onchange(currency: CurrencyView)
        fun updating(isUpdating: Boolean)
    }


}