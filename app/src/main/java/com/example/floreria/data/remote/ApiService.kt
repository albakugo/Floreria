package com.example.floreria.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ApiService {

    @GET("productos.php")
    suspend fun obtenerProductos(): List<ProductoResponse>

    @POST("productos.php")
    suspend fun agregarProducto(
        @Body producto: ProductoRequest
    ): Response<MensajeResponse>

    @PUT("productos.php")
    suspend fun actualizarProducto(
        @Body producto: ProductoRequest
    ): Response<MensajeResponse>

    @DELETE("productos.php")
    suspend fun eliminarProducto(
        @Query("id") id: Int
    ): Response<MensajeResponse>
}