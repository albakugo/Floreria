package com.example.floreria.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.floreria.ui.viewmodel.InventarioViewModel
import com.example.floreria.model.EstadoCaducidad

@Composable
fun LotesScreen(
    viewModel: InventarioViewModel
) {

    val lotes by viewModel.lotes.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Lotes de inventario",
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(lotes) { lote ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Lote: ${lote.id}"
                        )
                        Text(
                            text = "Flor ID: ${lote.florid}" // <-- Corregido a florid (minúscula)
                        )
                        Text(
                            text = "Cantidad: ${lote.cantidad}"
                        )
                        Text(
                            text = "Fecha de entrada: ${lote.fechaEntrada}"
                        )
                        Text(
                            text = "Fecha de caducidad: ${lote.fechaCaducidad}"
                        )

                        // Muestra la fecha de caducidad de forma segura
                        Text(
                            text = "Caducidad: ${lote.fechaCaducidad}"
                        )
                    }
                }
            }
        }
    }
}