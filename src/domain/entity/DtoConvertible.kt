package com.kuckjwi.ktor.domain.entity

interface DtoConvertible<D> {
    fun toDto(): D
}
