package com.example.floreria.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.floreria.model.Lote
import com.example.floreria.ui.viewmodel.InventarioViewModel

@Composable
fun AgregarLoteScreen(
    viewModel: InventarioViewModel
) {

    var florId by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var fechaEntrada by remember { mutableStateOf("") }
    var fechaCaducidad by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Agregar lote"
        )

        OutlinedTextField(
            value = florId,
            onValueChange = { florId = it },
            label = { Text("ID de la flor") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = cantidad,
            onValueChange = { cantidad = it },
            label = { Text("Cantidad") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = fechaEntrada,
            onValueChange = { fechaEntrada = it },
            label = { Text("Fecha de entrada") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = fechaCaducidad,
            onValueChange = { fechaCaducidad = it },
            label = { Text("Fecha de caducidad") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {

                if (
                    florId.isNotBlank() &&
                    cantidad.isNotBlank() &&
                    fechaEntrada.isNotBlank() &&
                    fechaCaducidad.isNotBlank()
                ) {

                    val nuevoLote = Lote(
                        id = System.currentTimeMillis().toInt(),
                        florid = florId.toInt(),
                        cantidad = cantidad.toInt(),
                        fechaEntrada = fechaEntrada,
                        fechaCaducidad = fechaCaducidad
                    )

                    viewModel.agregarLote(nuevoLote)

                    florId = ""
                    cantidad = ""
                    fechaEntrada = ""
                    fechaCaducidad = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar lote")
        }
    }
}