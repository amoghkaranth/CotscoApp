package com.example.cotscoapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: RestaurantsApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://opentable.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RestaurantsApi::class.java)
    }

}