package com.example.floreria.data.remote

data class ProductoRequest(
    val id: Int? = null,
    val nombre: String,
    val descripcion: String?,
    val precio: Double,
    val cantidad: Int,
    val imagen: String? = null,
)