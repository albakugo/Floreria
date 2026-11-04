package com.example.floreria.ui.navigation

import androidx.annotation.StringRes
import com.example.floreria.R

enum class FloraScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Inventory(title = R.string.app_name), // Cambia por R.string.inventario
    Sales(title = R.string.app_name),     // Cambia por R.string.ventas
    Orders(title = R.string.app_name),    // Cambia por R.string.pedidos
    Settings(title = R.string.app_name)   // Cambia por R.string.configuracion
}