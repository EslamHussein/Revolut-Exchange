package com.revolutan.data

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.revolutan.data.mapper.ExchangeRateMapper
import com.revolutan.data.repository.ExchangeRateDataStore
import com.revolutan.data.store.ExchangeRateDataStoreFactory
import com.revolutan.data.test.ExchangeRateFakeDataFactory
import com.revolutan.domain.interactor.exchangeRate.GetExchangeRateUseCase
import com.revolutan.domain.repository.ExchangeRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ExchangeRateDataRepositoryTest {


    private val exchangeRateMapper: ExchangeRateMapper = ExchangeRateMapper()
    @Mock
    private lateinit var exchangeRateDataStore: ExchangeRateDataStore
    @Mock
    private lateinit var exchangeRateDataStoreFactory: ExchangeRateDataStoreFactory
    private lateinit var exchangeRateRepository: ExchangeRepository

    private val entity = ExchangeRateFakeDataFactory.exchangeRateEntity()
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        exchangeRateRepository = ExchangeRateDataRepository(exchangeRateMapper, exchangeRateDataStoreFactory)
        whenever(exchangeRateDataStoreFactory.getDataStore()).thenReturn(exchangeRateDataStore)
        whenever(exchangeRateDataStore.getExchangeRate(any())).thenReturn(Observable.just(entity))
    }


    @Test
    fun getExchangeRateCompletes() {
        val testObserver =
            exchangeRateRepository.getExchangeRate(GetExchangeRateUseCase.Params.fromExchangeRate(entity.base)).test()
        testObserver.assertComplete()


    }
}