package com.example.floreria.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.floreria.ui.viewmodel.InventarioViewModel

@Composable
fun LotesScreen(
    onAgregarLoteClick: () -> Unit,
    // viewModel: InventarioViewModel = viewModel()
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAgregarLoteClick) {
                Text("+")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(text = "Gestión de Lotes", style = MaterialTheme.typography.headlineMedium)
            // Aquí pintas la lista de lotes leyendo del ViewModel
        }
    }
}