package com.olehmesh.repository.network

import com.olehmesh.repository.models.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/s/fk3d5kg6cptkpr6/menu.json?dl=1")
    suspend fun getMenuItems(): Response<ApiResponse>

}