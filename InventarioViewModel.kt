package com.example.floreria.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.floreria.data.InventarioRepository
import com.example.floreria.model.Flor
import com.example.floreria.model.Lote
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InventarioViewModel : ViewModel() {

    public val repository = InventarioRepository()

    public val _flores = MutableStateFlow(repository.obtenerFlores())
    val flores: StateFlow<List<Flor>> = _flores.asStateFlow()

    public val _lotes = MutableStateFlow(repository.obtenerLotes())
    val lotes: StateFlow<List<Lote>> = _lotes.asStateFlow()

    fun agregarLote(lote: Lote) {
        repository.agregarLote(lote)

        _lotes.update {
            repository.obtenerLotes()
        }
    }
}