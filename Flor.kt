package com.example.floreria.model

data class Flor(
    val id: Int,
    val nombre: String,
    val tipo: String,
    val precio: Double,
    val existencia: Int
)