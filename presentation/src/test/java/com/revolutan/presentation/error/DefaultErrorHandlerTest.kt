package com.revolutan.presentation.error

import com.revolutan.presentation.test.TestResources
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.net.SocketTimeoutException

class DefaultErrorHandlerTest {

    private val resource: TestResources = TestResources()
    private val errorHandler: ErrorHandler = DefaultErrorHandler(resource)

    @Before
    fun setUp() {
    }

    @Test
    fun getErrorMessage() {
        Assert.assertEquals(errorHandler.getErrorMessage(IOException()), "No internet connection")
        Assert.assertEquals(errorHandler.getErrorMessage(IllegalArgumentException()), "Unknown error")
    }

}