package com.revolutan.exchange.exchangerate

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.revolutan.exchange.R
import com.revolutan.presentation.ExchangeRateViewModel
import com.revolutan.presentation.model.CurrencyView
import com.revolutan.presentation.state.ResourceState
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(),
    ExchangeRateAdapter.ExchangeRateOnChangingListener {
    private val viewModel: ExchangeRateViewModel by inject()
    private lateinit var exchangeRateAdapter: ExchangeRateAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        exchangeRateAdapter = ExchangeRateAdapter(onChangingListener = this)
        viewManager = LinearLayoutManager(this)

        exchangeRateRecyclerView.apply {
            layoutManager = viewManager
            adapter = exchangeRateAdapter
        }


        viewModel.getExchangeRate().observe(this, Observer {
            when (it.status) {
                ResourceState.SUCCESS -> {
                    updateExchangeRate(it.data?.rates!!)
                    updateCurrenciesList(it.data?.currencies!!)
                    hideLoading()
                    hideMessage()
                }
                ResourceState.ERROR -> {
                    hideLoading()
                    showError(it.message!!)
                }
                ResourceState.LOADING -> {
                    showLoading()
                    showError(getString(R.string.loading))
                }
            }
        })

        viewModel.updateExchangeRate()
    }

    private fun showLoading() {
        if (exchangeRateAdapter.itemCount == 0) {
            progressBar.visibility = View.VISIBLE
            statusTextView.text = getString(R.string.loading)
            statusTextView.visibility = View.VISIBLE
        }

    }

    private fun hideLoading() {
        progressBar.visibility = View.INVISIBLE

    }

    private fun hideMessage() {
        statusTextView.visibility = View.INVISIBLE
    }

    private fun showError(errorMsg: String) {

        if (exchangeRateAdapter.itemCount == 0) {
            statusTextView.text = errorMsg
            statusTextView.visibility = View.VISIBLE
        } else {
            Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
        }

    }

    private fun updateExchangeRate(rates: Map<String, Double>) {
        exchangeRateAdapter.updateRates(rates)
    }

    private fun updateCurrenciesList(currencies: List<CurrencyView>) {
        exchangeRateAdapter.updateData(currencies)
    }

    override fun onchange(currency: CurrencyView) {
        viewModel.changeBaseExchangeCurrency(currency)
    }

    override fun updating(isUpdating: Boolean) {
        viewModel.pauseUpdate(isUpdating)
    }

}
