package com.example.floreria.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.floreria.R
import com.example.floreria.ui.navigation.FloraScreen
import com.example.floreria.ui.screens.HomeScreen
import com.example.floreria.ui.screens.SettingsScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FloraApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Búsqueda segura del Enum según la ruta actual
    val currentRoute = backStackEntry?.destination?.route
    val currentScreen = FloraScreen.entries.find { it.name == currentRoute } ?: FloraScreen.Start

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )
                HorizontalDivider()

                FloraScreen.entries.forEach { screen ->
                    NavigationDrawerItem(
                        label = { Text(stringResource(screen.title)) },
                        selected = currentScreen == screen,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate(screen.name) {
                                popUpTo(FloraScreen.Start.name)
                                launchSingleTop = true
                            }
                        },
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(stringResource(currentScreen.title)) },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menú")
                        }
                    }
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = FloraScreen.Start.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                // Inicio
                composable(route = FloraScreen.Start.name) {
                    HomeScreen()
                }

                // Configuración
                composable(route = FloraScreen.Settings.name) {
                    SettingsScreen()
                }

                composable(route = FloraScreen.Inventory.name) {
                    PantallaProvisional("Módulo Inventario")
                }

                composable(route = FloraScreen.Sales.name) {
                    PantallaProvisional("Módulo Ventas")
                }

                composable(route = FloraScreen.Orders.name) {
                    PantallaProvisional("Módulo Pedidos")
                }
            }
        }
    }
}

@Composable
fun PantallaProvisional(titulo: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = titulo, style = MaterialTheme.typography.titleMedium)
    }
}