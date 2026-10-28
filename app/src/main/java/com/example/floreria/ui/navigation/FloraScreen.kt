package com.example.floreria.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.floreria.ui.screens.*

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Catalogo : Screen("catalogo")
    object AgregarLote : Screen("agregar_lote")
    object Lotes : Screen("lotes")
    object CarritoPago : Screen("carrito_pago")
    object HistorialVentas : Screen("historial_ventas")
    object SeguimientoPedido : Screen("seguimiento_pedido")
    object Settings : Screen("settings")
}

@Composable
fun FloraApp(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToCatalogo = { navController.navigate(Screen.Catalogo.route) },
                onNavigateToLotes = { navController.navigate(Screen.Lotes.route) },
                onNavigateToSettings = { navController.navigate(Screen.Settings.route) }
            )
        }
        composable(Screen.Catalogo.route) {
            CatalogoScreen(onNavigateToCarrito = { navController.navigate(Screen.CarritoPago.route) })
        }
        composable(Screen.AgregarLote.route) {
            AgregarLoteScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Lotes.route) {
            LotesScreen(onAgregarLoteClick = { navController.navigate(Screen.AgregarLote.route) })
        }
        composable(Screen.CarritoPago.route) {
            CarritoPagoScreen(onPagoExitoso = { navController.navigate(Screen.SeguimientoPedido.route) })
        }
        composable(Screen.HistorialVentas.route) {
            HistorialVentasScreen()
        }
        composable(Screen.SeguimientoPedido.route) {
            SeguimientoPedidoScreen()
        }
        composable(Screen.Settings.route) {
            SettingsScreen()
        }
    }
}