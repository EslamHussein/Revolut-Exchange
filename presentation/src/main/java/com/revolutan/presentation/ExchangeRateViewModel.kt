package com.revolutan.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revolutan.domain.interactor.exchangeRate.GetCurrenciesUseCase
import com.revolutan.domain.interactor.exchangeRate.GetExchangeRateUseCase
import com.revolutan.domain.model.Currency
import com.revolutan.domain.model.ExchangeRate
import com.revolutan.presentation.mapper.CurrenciesViewMapper
import com.revolutan.presentation.mapper.ExchangeRateViewMapper
import com.revolutan.presentation.model.CurrencyView
import com.revolutan.presentation.model.ExchangeRateView
import com.revolutan.presentation.state.Resource
import com.revolutan.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver

class ExchangeRateViewModel(
    private val getExchangeRateUseCase: GetExchangeRateUseCase,
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val exchangeRateViewMapper: ExchangeRateViewMapper,
    private val currenciesViewMapper: CurrenciesViewMapper
) : ViewModel() {

    private val exchangeRateLiveDate: MutableLiveData<Resource<ExchangeRateView>> = MutableLiveData()
    private val currencyListLiveData: MutableLiveData<Resource<List<CurrencyView>>> = MutableLiveData()
    private var currentExchangeCurrency: String = "EUR"


    fun getExchangeRate(): LiveData<Resource<ExchangeRateView>> {
        return exchangeRateLiveDate
    }

    fun getCurrencyListLiveData(): LiveData<Resource<List<CurrencyView>>> {
        return currencyListLiveData
    }

    fun changeBaseExchangeCurrency(base: String) {
        currentExchangeCurrency = base
    }

    fun updateExchangeRate() {
        exchangeRateLiveDate.postValue(Resource(ResourceState.LOADING, null, null))
        getExchangeRateUseCase.execute(
            ExchangeRateSubscriber(),
            GetExchangeRateUseCase.Params.fromExchangeRate(currentExchangeCurrency)
        )
    }

    override fun onCleared() {
        getExchangeRateUseCase.dispose()
        super.onCleared()
    }

    inner class ExchangeRateSubscriber : DisposableObserver<ExchangeRate>() {
        override fun onComplete() {

        }

        override fun onNext(exchangerate: ExchangeRate) {

            if (currencyListLiveData.value == null) {
                getCurrenciesUseCase.execute(
                    GetCurrencyListSubscriber(),
                    GetCurrenciesUseCase.Params.createFromCurrencies(exchangerate.base, exchangerate.rates)
                )

            }
            exchangeRateLiveDate.postValue(
                Resource(
                    ResourceState.SUCCESS,
                    exchangeRateViewMapper.mapToView(exchangerate),
                    null
                )
            )
        }

        override fun onError(e: Throwable) {
            exchangeRateLiveDate.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }

    inner class GetCurrencyListSubscriber : DisposableSingleObserver<List<Currency>>() {
        override fun onSuccess(currencies: List<Currency>) {
            val data = currencies.map {
                currenciesViewMapper.mapToView(it)
            }
            currencyListLiveData.postValue(Resource(ResourceState.SUCCESS, data, null))
        }

        override fun onError(e: Throwable) {
            currencyListLiveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }

}