package com.revolutan.domain.interactor

internal interface SynchronousUseCase<out Results, in Params> {
    fun execute(params: Params? = null): Results
}