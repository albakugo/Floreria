package com.example.floreria.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.floreria.model.Venta
import com.example.floreria.repository.VentasRepository
import com.example.floreria.repository.VentasRepositoryImpl
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class HistorialVentasViewModel(
    repository: VentasRepository = VentasRepositoryImpl()
) : ViewModel() {

    val listaVentas: StateFlow<List<Venta>> = repository.obtenerVentas()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}