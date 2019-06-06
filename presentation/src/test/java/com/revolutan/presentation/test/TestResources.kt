package com.revolutan.presentation.test

import android.content.res.Resources
import com.revolutan.presentation.R

internal class TestResources : Resources(null, null, null) {

    @Throws(Resources.NotFoundException::class)
    override fun getString(id: Int): String {
        when (id) {
            R.string.no_internet_connection -> return "No internet connection"
            R.string.timeout_error_message -> return "Connection timeout"
            R.string.unknown_error -> return "Unknown error"
        }

        throw Resources.NotFoundException()
    }
}