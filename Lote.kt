package com.example.floreria.model

data class Lote(
    val id: Int,
    val florid: Int,
    val cantidad: Int,
    val fechaEntrada: String,
    val fechaCaducidad: String,
)