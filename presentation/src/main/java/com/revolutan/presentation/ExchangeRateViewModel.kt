package com.revolutan.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revolutan.domain.interactor.exchangeRate.GetExchangeRateUseCase
import com.revolutan.domain.interactor.exchangeRate.SortCurrenciesUseCase
import com.revolutan.domain.model.Currency
import com.revolutan.domain.model.ExchangeRate
import com.revolutan.presentation.error.ErrorHandler
import com.revolutan.presentation.mapper.CurrenciesViewMapper
import com.revolutan.presentation.model.CurrencyView
import com.revolutan.presentation.model.ExchangeView
import com.revolutan.presentation.state.Resource
import com.revolutan.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver

class ExchangeRateViewModel(
    private val errorHandler: ErrorHandler,
    private val getExchangeRateUseCase: GetExchangeRateUseCase,
    private val sortCurrenciesUseCase: SortCurrenciesUseCase,
    private val currenciesViewMapper: CurrenciesViewMapper
) : ViewModel() {

    private val exchangeRateLiveDate: MutableLiveData<Resource<ExchangeView>> = MutableLiveData()
    private var currentExchangeCurrency: Currency = Currency("EUR")
    private var isUpdating: Boolean = false


    fun getExchangeRate(): LiveData<Resource<ExchangeView>> {
        return exchangeRateLiveDate
    }

    fun changeBaseExchangeCurrency(currency: CurrencyView) {
        currentExchangeCurrency = currenciesViewMapper.mapFromView(currency)
        getExchangeRateUseCase.clear()
        updateExchangeRate()
    }

    fun updateExchangeRate() {
        exchangeRateLiveDate.postValue(Resource(ResourceState.LOADING, null, null))
        getExchangeRateUseCase.execute(
            ExchangeRateSubscriber(),
            GetExchangeRateUseCase.Params.fromExchangeRate(currentExchangeCurrency.currency)
        )
    }

    override fun onCleared() {
        getExchangeRateUseCase.dispose()
        super.onCleared()
    }

    fun pauseUpdate(isUpdating: Boolean) {
        this.isUpdating = isUpdating
    }

    inner class ExchangeRateSubscriber() :
        DisposableObserver<ExchangeRate>() {
        override fun onComplete() {

        }

        override fun onNext(exchangeRate: ExchangeRate) {

            if (isUpdating)
                return
            val sortParams = SortCurrenciesUseCase.Params.fromSortCurrencies(
                currentExchangeCurrency,
                exchangeRate.rates
            )
            val result = sortCurrenciesUseCase.execute(sortParams).map {
                currenciesViewMapper.mapToView(it)
            }

            exchangeRateLiveDate.postValue(
                Resource(
                    ResourceState.SUCCESS,
                    ExchangeView(result, exchangeRate.rates),
                    null
                )
            )

        }

        override fun onError(e: Throwable) {
            exchangeRateLiveDate.postValue(Resource(ResourceState.ERROR, null, errorHandler.getErrorMessage(e)))
        }
    }


}