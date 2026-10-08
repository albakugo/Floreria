package com.example.floreria.ui.navigation

import androidx.annotation.StringRes
import com.example.floreria.R

enum class FloraScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Inventory(title = R.string.menu_inventory),
    Sales(title = R.string.menu_sales),
    Orders(title = R.string.menu_orders),
    Settings(title = R.string.menu_settings)
}