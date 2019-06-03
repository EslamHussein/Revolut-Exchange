package com.revolutan.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.revolutan.domain.interactor.exchangeRate.GetCurrenciesUseCase
import com.revolutan.domain.interactor.exchangeRate.GetExchangeRateUseCase
import com.revolutan.domain.model.ExchangeRate
import com.revolutan.presentation.mapper.CurrenciesViewMapper
import com.revolutan.presentation.mapper.ExchangeRateViewMapper
import io.reactivex.observers.DisposableObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ExchangeRateViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getExchangeRateUseCase: GetExchangeRateUseCase

    @Mock
    private lateinit var getCurrenciesUseCase: GetCurrenciesUseCase

    @Mock
    private lateinit var exchangeRateViewMapper: ExchangeRateViewMapper
    @Mock
    private lateinit var currenciesViewMapper: CurrenciesViewMapper


    private lateinit var exchangeRateViewModel: ExchangeRateViewModel

    @Captor
    lateinit var captor: ArgumentCaptor<DisposableObserver<ExchangeRate>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        exchangeRateViewModel = ExchangeRateViewModel(
            getExchangeRateUseCase, getCurrenciesUseCase,
            exchangeRateViewMapper, currenciesViewMapper
        )
    }

    @Test
    fun test() {
        exchangeRateViewModel.updateExchangeRate()
        verify(getExchangeRateUseCase, times(1)).execute(any(), null)
    }

    @Test
    fun tests() {
        exchangeRateViewModel.updateExchangeRate()
        verify(getExchangeRateUseCase, times(1)).execute(any(), null)
    }
}