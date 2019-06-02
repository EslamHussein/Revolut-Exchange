package com.revolutan.remote

import com.revolutan.data.repository.ExchangeRateRemote
import com.revolutan.remote.di.remoteModule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import retrofit2.HttpException
import java.io.File

class ExchangeRateRemoteImplTest : AutoCloseKoinTest() {
    private lateinit var mockServer: MockWebServer
    private lateinit var rxchangeRateRemote: ExchangeRateRemote


    @Before
    fun setUp() {
        mockServer = MockWebServer()
        mockServer.start()

        startKoin {
            modules(listOf(remoteModule))
        }
        rxchangeRateRemote = get()

    }

    @Test
    fun `test success response from server`() {
        val base = "EUR"
        val mockedResponse = MockResponse().apply {
            setResponseCode(200)
            setBody(getJson("json/exchangeRate/last.json"))
        }
        mockServer.enqueue(mockedResponse)
        val resultObservable = rxchangeRateRemote.getExchangeRate(base).test()
        resultObservable.assertValue {
            it.base == base
        }

    }


    @Test
    fun `test failure response from server with wrong base input`() {
        val base = "EU"

        val mockedResponse = MockResponse().apply {
            setResponseCode(422)
            setBody(getJson("json/exchangeRate/lastWithWrongInput.json"))
        }

        mockServer.enqueue(mockedResponse)

        val resultObservable = rxchangeRateRemote.getExchangeRate(base).test()

        resultObservable.assertError {
            it is HttpException
        }
    }

    private fun getJson(path: String): String {
        // Load the JSON response
        val uri = this.javaClass.classLoader?.getResource(path)
        val file = File(uri?.path)
        return String(file.readBytes())
    }
}