package com.revolutan.data.store

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ExchangeRateDataStoreFactoryTest {
    @Mock
    private lateinit var exchangeRateRemoteDataStore: ExchangeRateRemoteDataStore
    private lateinit var exchangeRateDataStoreFactory: ExchangeRateDataStoreFactory
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        exchangeRateDataStoreFactory = ExchangeRateDataStoreFactory(exchangeRateRemoteDataStore)
    }

    @Test
    fun getDataStoreReturnRemoteDataStore() {
        Assert.assertEquals(exchangeRateRemoteDataStore, exchangeRateDataStoreFactory.getDataStore())
    }

    @Test
    fun getDataStoreNotNull() {
        Assert.assertNotNull(exchangeRateDataStoreFactory.getDataStore())
    }
}