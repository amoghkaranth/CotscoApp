package com.example.cotscoapp

import retrofit2.Response
import retrofit2.http.GET

interface RestaurantsApi {
    @GET("api/restaurants")
    suspend fun getRestaurants(): Response<List<Restaurants>>
}