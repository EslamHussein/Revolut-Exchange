package com.revolutan.presentation.state

class Resource<T>(
    val status: ResourceState,
    val data: T? = null,
    val message: String? = null
)