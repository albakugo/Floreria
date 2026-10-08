package com.example.floreria.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.floreria.R
import com.example.floreria.ui.viewmodel.SettingsViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel = viewModel()
) {
    val uiState by settingsViewModel.uiState.collectAsState()
    var showTimePicker by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.menu_settings),
            style = MaterialTheme.typography.headlineSmall
        )

        HorizontalDivider()

        // Caja de Texto
        OutlinedTextField(
            value = uiState.storeName,
            onValueChange = { settingsViewModel.updateStoreName(it) },
            label = { Text("Nombre del Establecimiento") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        // Switch
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Tema Oscuro")
            Switch(
                checked = uiState.isDarkMode,
                onCheckedChange = { settingsViewModel.toggleDarkMode(it) }
            )
        }

        // Checkbox
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = uiState.autoSyncEnabled,
                onCheckedChange = { settingsViewModel.toggleAutoSync(it) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Sincronizar automáticamente en la nube")
        }

        HorizontalDivider()

        // RadioButtons (Idioma)
        Text(
            text = "Idioma de la aplicación",
            style = MaterialTheme.typography.titleMedium
        )

        val languages = listOf("Español" to "ES", "English" to "EN")
        languages.forEach { (label, code) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (uiState.selectedLanguage == code),
                        onClick = { settingsViewModel.updateLanguage(code) }
                    )
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (uiState.selectedLanguage == code),
                    onClick = { settingsViewModel.updateLanguage(code) }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = label)
            }
        }

        HorizontalDivider()

        // Botón y TimePicker / Diálogo
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Hora de Cierre: ${uiState.closingTime}")
            Button(onClick = { showTimePicker = true }) {
                Text(text = "Cambiar Hora")
            }
        }

        if (showTimePicker) {
            TimePickerDialog(
                onDismiss = { showTimePicker = false },
                onConfirm = { hour, minute ->
                    settingsViewModel.updateClosingTime(
                        String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
                    )
                    showTimePicker = false
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    onDismiss: () -> Unit,
    onConfirm: (Int, Int) -> Unit
) {
    val timePickerState = rememberTimePickerState(initialHour = 20, initialMinute = 0)

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = { onConfirm(timePickerState.hour, timePickerState.minute) }) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        },
        text = {
            TimePicker(state = timePickerState)
        }
    )
}