package com.revolutan.presentation.mapper

interface Mapper<V, D> {
    fun mapToView(type: D): V
    fun mapFromView(view: V): D
}