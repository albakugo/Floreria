package com.example.floreria.model

import java.util.UUID

enum class EstadoPedido(val etiqueta: String) {
    PENDIENTE("Pendiente"),
    EN_PREPARACION("En Preparación"),
    EN_CAMINO("En Camino"),
    ENTREGADO("Entregado"),
    CANCELADO("Cancelado")
}

data class Cliente(
    val id: String = UUID.randomUUID().toString(),
    val nombre: String,
    val telefono: String,
    val direccion: String
)

data class Pedido(
    val id: String = UUID.randomUUID().toString(),
    val cliente: Cliente,
    val items: List<ItemCarrito>,
    val total: Double,
    val estado: EstadoPedido = EstadoPedido.PENDIENTE,
    val fecha: Long = System.currentTimeMillis()
)