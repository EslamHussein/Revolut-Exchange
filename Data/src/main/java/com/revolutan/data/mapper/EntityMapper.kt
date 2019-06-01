package com.revolutan.data.mapper

interface EntityMapper<E, D> {

    fun mapFromEntity(entity: E): D
    fun mapFromData(data: D): E
}