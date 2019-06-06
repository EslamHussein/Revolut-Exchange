package com.revolutan.presentation.error

interface ErrorHandler {
    fun getErrorMessage(error: Throwable): String
}