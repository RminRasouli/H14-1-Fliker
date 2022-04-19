package com.armin.hw14.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NEtworkManager {
    const val BASE_URL = " https://www.flickr.com"
    val api : ApiRequest = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiRequest::class.java)
}