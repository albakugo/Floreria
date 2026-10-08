package com.example.floreria.repository

import com.example.floreria.model.Venta
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

interface VentasRepository {
    fun obtenerVentas(): Flow<List<Venta>>
    suspend fun registrarVenta(venta: Venta)
}

class VentasRepositoryImpl : VentasRepository {

    private val _ventas = MutableStateFlow<List<Venta>>(emptyList())

    override fun obtenerVentas(): Flow<List<Venta>> = _ventas.asStateFlow()

    override suspend fun registrarVenta(venta: Venta) {
        _ventas.update { listaActual ->
            listaActual + venta
        }
    }
}