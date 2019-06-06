package com.revolutan.domain.interactor.exchangeRate

import com.nhaarman.mockitokotlin2.whenever
import com.revolutan.domain.executer.ExecutionThread
import com.revolutan.domain.interactor.test.ExchangeRateDataFactory
import com.revolutan.domain.repository.ExchangeRepository
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetExchangeRateUseCaseTest {
    private lateinit var getExchangeRateUseCase: GetExchangeRateUseCase
    @Mock
    lateinit var exchangeRepository: ExchangeRepository
    @Mock
    lateinit var executionThread: ExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getExchangeRateUseCase = GetExchangeRateUseCase(exchangeRepository, executionThread)

    }

    @Test
    fun getProjectsSuccess() {

        val result = ExchangeRateDataFactory.generateExchangeRate()

        // Given

        val testParam = GetExchangeRateUseCase.Params.fromExchangeRate("USD")


        whenever(exchangeRepository.getExchangeRate(testParam))
            .thenReturn(Observable.just(result))

        // When
        val testObservable = getExchangeRateUseCase.buildUseCaseObservable(testParam).test()

        // Then
        testObservable.assertComplete()

    }



    @Test(expected = IllegalArgumentException::class)
    fun getProjectsFailureWithNullParams() {

        // Given
        val testParam = null

        // When
        val testObservable = getExchangeRateUseCase.buildUseCaseObservable(testParam).test()

        // Then
        testObservable.assertComplete()

    }

}