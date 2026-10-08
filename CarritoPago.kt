package com.example.floreria.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.floreria.model.Flor
import com.example.floreria.model.FormaPago
import com.example.floreria.model.ItemCarrito
import com.example.floreria.model.Venta
import com.example.floreria.repository.VentasRepository
import com.example.floreria.repository.VentasRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CarritoPagoUiState(
    val carrito: List<ItemCarrito> = emptyList(),
    val formaPagoSeleccionada: FormaPago = FormaPago.EFECTIVO
) {
    val total: Double
        get() = carrito.sumOf { it.subtotal }
}

class CarritoPagoViewModel(
    private val repository: VentasRepository = VentasRepositoryImpl()
) : ViewModel() {

    private val _uiState = MutableStateFlow(CarritoPagoUiState())
    val uiState: StateFlow<CarritoPagoUiState> = _uiState.asStateFlow()

    fun agregarAlCarrito(flor: Flor) {
        _uiState.update { estado ->
            val existe = estado.carrito.find { it.flor.id == flor.id }
            val carritoActualizado = if (existe != null) {
                estado.carrito.map { item ->
                    if (item.flor.id == flor.id) {
                        item.copy(cantidad = item.cantidad + 1)
                    } else item
                }
            } else {
                estado.carrito + ItemCarrito(flor = flor, cantidad = 1)
            }
            estado.copy(carrito = carritoActualizado)
        }
    }

    fun cambiarCantidad(item: ItemCarrito, nuevaCantidad: Int) {
        if (nuevaCantidad <= 0) {
            eliminarDelCarrito(item)
            return
        }
        _uiState.update { estado ->
            val carritoActualizado = estado.carrito.map {
                if (it.flor.id == item.flor.id) it.copy(cantidad = nuevaCantidad) else it
            }
            estado.copy(carrito = carritoActualizado)
        }
    }

    fun eliminarDelCarrito(item: ItemCarrito) {
        _uiState.update { estado ->
            estado.copy(carrito = estado.carrito.filter { it.flor.id != item.flor.id })
        }
    }

    fun seleccionarFormaPago(formaPago: FormaPago) {
        _uiState.update { it.copy(formaPagoSeleccionada = formaPago) }
    }

    fun registrarVenta(onExito: () -> Unit) {
        val estado = _uiState.value
        if (estado.carrito.isEmpty()) return

        val nuevaVenta = Venta(
            items = estado.carrito,
            total = estado.total,
            formaPago = estado.formaPagoSeleccionada
        )

        viewModelScope.launch {
            repository.registrarVenta(nuevaVenta)
            _uiState.update { CarritoPagoUiState() }
            onExito()
        }
    }
}