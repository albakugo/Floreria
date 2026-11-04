
package com.example.floreria.data.repository

import com.example.floreria.data.remote.ProductoRequest
import com.example.floreria.data.remote.ProductoResponse
import com.example.floreria.data.remote.RetrofitClient

class ProductoRepository {

    private val api = RetrofitClient.api

    suspend fun obtenerProductos(): List<ProductoResponse> {
        return api.obtenerProductos()
    }

    suspend fun agregarProducto(
        producto: ProductoRequest
    ) = api.agregarProducto(producto)

    suspend fun actualizarProducto(
        producto: ProductoRequest
    ) = api.actualizarProducto(producto)

    suspend fun eliminarProducto(
        id: Int
    ) = api.eliminarProducto(id)
}
