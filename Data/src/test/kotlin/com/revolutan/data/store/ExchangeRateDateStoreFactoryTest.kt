package com.revolutan.data.store

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ExchangeRateDateStoreFactoryTest {
    @Mock
    private lateinit var exchangeRateRemoteDataStore: ExchangeRateRemoteDataStore
    private lateinit var exchangeRateDateStoreFactory: ExchangeRateDateStoreFactory
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        exchangeRateDateStoreFactory = ExchangeRateDateStoreFactory(exchangeRateRemoteDataStore)
    }

    @Test
    fun getDataStoreReturnRemoteDataStore() {
        Assert.assertEquals(exchangeRateRemoteDataStore, exchangeRateDateStoreFactory.getDataStore())
    }

    @Test
    fun getDataStoreNotNull() {
        Assert.assertNotNull(exchangeRateDateStoreFactory.getDataStore())
    }
}