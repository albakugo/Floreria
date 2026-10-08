package com.example.floreria.model

import java.util.UUID

enum class FormaPago(val titulo: String) {
    EFECTIVO("Efectivo"),
    TARJETA("Tarjeta de Débito/Crédito"),
    TRANSFERENCIA("Transferencia / SPEI")
}

data class ItemCarrito(
    val flor: Flor,
    val cantidad: Int = 1
) {
    val subtotal: Double
        get() = flor.precio * cantidad
}

data class Venta(
    val id: String = UUID.randomUUID().toString(),
    val items: List<ItemCarrito>,
    val total: Double,
    val formaPago: FormaPago,
    val fecha: Long = System.currentTimeMillis()
)