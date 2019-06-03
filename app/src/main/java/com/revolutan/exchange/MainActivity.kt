package com.revolutan.exchange

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.revolutan.presentation.ExchangeRateViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val viewModel: ExchangeRateViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.getExchangeRate().observe(this, Observer {
            Toast.makeText(this, it.status.toString(), Toast.LENGTH_LONG).show()
        })

        viewModel.getCurrencyListLiveData().observe(this, Observer {
            Toast.makeText(this, "${it.data?.size}", Toast.LENGTH_LONG).show()
        })
        viewModel.updateExchangeRate()
    }
}
