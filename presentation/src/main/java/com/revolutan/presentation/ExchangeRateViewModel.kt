package com.revolutan.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revolutan.domain.interactor.exchangeRate.GetExchangeRateUseCase
import com.revolutan.domain.model.ExchangeRate
import com.revolutan.presentation.mapper.ExchangeRateViewMapper
import com.revolutan.presentation.model.ExchangeRateView
import com.revolutan.presentation.state.Resource
import com.revolutan.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver

class ExchangeRateViewModel(
    private val getExchangeRateUseCase: GetExchangeRateUseCase,
    private val mapper: ExchangeRateViewMapper
) : ViewModel() {

    private val exchangeRateLiveDate: MutableLiveData<Resource<ExchangeRateView>> = MutableLiveData()

    fun getExchangeRate(): LiveData<Resource<ExchangeRateView>> {
        return exchangeRateLiveDate
    }

    fun updateExchangeRate() {
        exchangeRateLiveDate.postValue(Resource(ResourceState.LOADING, null, null))
        getExchangeRateUseCase.execute(ExchangeRateSubscriber())
    }

    override fun onCleared() {
        getExchangeRateUseCase.dispose()
        super.onCleared()
    }

    inner class ExchangeRateSubscriber : DisposableObserver<ExchangeRate>() {
        override fun onComplete() {

        }

        override fun onNext(t: ExchangeRate) {
            exchangeRateLiveDate.postValue(Resource(ResourceState.SUCCESS, mapper.mapToView(t), null))
        }

        override fun onError(e: Throwable) {
            exchangeRateLiveDate.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }
}