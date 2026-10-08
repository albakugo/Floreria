package com.example.floreria.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.floreria.model.Cliente
import com.example.floreria.model.EstadoPedido
import com.example.floreria.model.Flor
import com.example.floreria.model.ItemCarrito
import com.example.floreria.model.Pedido
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class PedidosUiState(
    val pedidos: List<Pedido> = emptyList(),
    val filtroEstado: EstadoPedido? = null,
    val busquedaCliente: String = ""
)

class SeguimientoPedidosViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PedidosUiState())
    val uiState: StateFlow<PedidosUiState> = _uiState.asStateFlow()

    init {
        cargarPedidosEjemplo()
    }

    private fun cargarPedidosEjemplo() {
        val clienteEjemplo = Cliente(
            nombre = "María García",
            telefono = "4412345678",
            direccion = "Av. Hidalgo #123, Centro"
        )

        // Corregido: id como Int, y se añaden tipo y existencia requeridos por el modelo Flor
        val florEjemplo = Flor(
            id = 1,
            nombre = "Rosas Rojas",
            precio = 250.0,
            tipo = "Natural",
            existencia = 15
        )

        val pedidosIniciales = listOf(
            Pedido(
                cliente = clienteEjemplo,
                items = listOf(ItemCarrito(flor = florEjemplo, cantidad = 2)),
                total = 500.0,
                estado = EstadoPedido.EN_PREPARACION
            )
        )

        _uiState.update { it.copy(pedidos = pedidosIniciales) }
    }

    fun actualizarEstadoPedido(pedidoId: String, nuevoEstado: EstadoPedido) {
        _uiState.update { estado ->
            val actualizados = estado.pedidos.map { pedido ->
                if (pedido.id == pedidoId) pedido.copy(estado = nuevoEstado) else pedido
            }
            estado.copy(pedidos = actualizados)
        }
    }

    fun buscarPorCliente(query: String) {
        _uiState.update { it.copy(busquedaCliente = query) }
    }

    fun filtrarPorEstado(estado: EstadoPedido?) {
        _uiState.update { it.copy(filtroEstado = estado) }
    }
}