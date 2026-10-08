package com.example.floreria.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.floreria.ui.navigation.FloraScreen
import com.example.floreria.ui.viewmodel.InventarioViewModel

@Composable
fun CatalogoScreen(
    viewModel: InventarioViewModel,
    navController: NavHostController
) {
    val flores by viewModel.flores.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Catálogo de flores",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                navController.navigate("agregar_lote")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar lote")
        }

        Button(
            onClick = {
                navController.navigate("lotes")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text("Ver lotes")
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(flores) { flor ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = flor.nombre)
                        Text(text = "Tipo: ${flor.tipo}")
                        Text(text = "Precio: $${flor.precio}")
                        Text(text = "Existencia: ${flor.existencia}")
                    }
                }
            }
        }
    }
}