package com.example.floreria.data.remote

data class ProductoResponse(
    val id: Int,
    val nombre: String,
    val descripcion: String?,
    val precio: Double,
    val cantidad: Int,
    val imagen: String?,
    val fecha_registro: String?
)