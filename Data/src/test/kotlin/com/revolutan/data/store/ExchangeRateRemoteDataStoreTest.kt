package com.revolutan.data.store

import com.nhaarman.mockitokotlin2.whenever
import com.revolutan.data.model.ExchangeRateEntity
import com.revolutan.data.repository.ExchangeRateDataStore
import com.revolutan.data.repository.ExchangeRateRemote
import com.revolutan.data.test.ExchangeRateFakeDataFactory
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ExchangeRateRemoteDataStoreTest {
    @Mock
    private lateinit var remote: ExchangeRateRemote
    private lateinit var remoteDataStore: ExchangeRateDataStore
    private lateinit var exchangeRateResponse: ExchangeRateEntity

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        exchangeRateResponse = ExchangeRateFakeDataFactory.exchangeRateEntity()
        remoteDataStore = ExchangeRateRemoteDataStore(remote)
        whenever(remote.getExchangeRate("USD")).thenReturn(Observable.just(exchangeRateResponse))
    }

    @Test
    fun getExchangeRateReturnData() {
        val testObservable = remoteDataStore.getExchangeRate("USD").test()
        testObservable.assertValue(exchangeRateResponse)
        testObservable.assertComplete()
    }
}