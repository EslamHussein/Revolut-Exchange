package com.revolutan.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.revolutan.domain.interactor.exchangeRate.GetExchangeRateUseCase
import com.revolutan.domain.interactor.exchangeRate.SortCurrenciesUseCase
import com.revolutan.domain.model.ExchangeRate
import com.revolutan.presentation.ExchangeRateViewModel.ExchangeRateSubscriber
import com.revolutan.presentation.error.ErrorHandler
import com.revolutan.presentation.mapper.CurrenciesViewMapper
import com.revolutan.presentation.state.ResourceState
import com.revolutan.presentation.test.ExchangeRateFakeDataFactory
import io.reactivex.observers.DisposableObserver
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.IOException

@RunWith(JUnit4::class)
class ExchangeRateViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private var getExchangeRateUseCase = mock<GetExchangeRateUseCase>()

    private var sortCurrenciesUseCase = mock<SortCurrenciesUseCase>()

    private var currenciesViewMapper = mock<CurrenciesViewMapper>()

    private var errorHandler = mock<ErrorHandler>()


    private lateinit var exchangeRateViewModel: ExchangeRateViewModel


    @Before
    fun setUp() {

        exchangeRateViewModel = ExchangeRateViewModel(
            errorHandler,
            getExchangeRateUseCase,
            sortCurrenciesUseCase,
            currenciesViewMapper
        )
    }

    @Test
    fun getExchangeRateExecutesUseCase() {
        exchangeRateViewModel.updateExchangeRate()

        verify(getExchangeRateUseCase, times(1)).execute(any(), anyOrNull())
    }

    @Test
    fun `get exchange rate success`() {
        val exchangeRate = ExchangeRateFakeDataFactory.getExchangeRate()

        given(getExchangeRateUseCase.execute(any(), eq(GetExchangeRateUseCase.Params(base = "EUR")))).will {
            val observer = it.arguments[0] as DisposableObserver<ExchangeRate>
            observer.onNext(exchangeRate)
            observer.onComplete()

        }
        exchangeRateViewModel.updateExchangeRate()
        Assert.assertEquals(ResourceState.SUCCESS, exchangeRateViewModel.getExchangeRate().value?.status)
    }

    @Test
    fun `get exchange rate failure no internet connection`() {

        given(getExchangeRateUseCase.execute(any(), eq(GetExchangeRateUseCase.Params(base = "EUR")))).will {
            val observer = it.arguments[0] as DisposableObserver<ExchangeRate>
            observer.onError(IOException("No internet connection"))
            observer.onComplete()
        }
        exchangeRateViewModel.updateExchangeRate()
        Assert.assertEquals(ResourceState.ERROR, exchangeRateViewModel.getExchangeRate().value?.status)
    }
}