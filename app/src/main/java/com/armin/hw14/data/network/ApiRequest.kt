package com.armin.hw14.data.network

import com.armin.hw14.data.network.Model.PhotoJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {
    @GET("/services/rest/")
    fun getForShowPhoto(
        @Query("api_key") api_Key: String,
        @Query("method") method: String,
        @Query("user_id") user_Id: String,
        @Query("extras") extras: String,
        @Query("format") format: String,
        @Query("nojsoncallback") noJsonCallback: Int,
        @Query("per_page") per_Page: Int,
    ): Call<PhotoJson>

    @GET("/services/rest/")
    fun getSearch(
        @Query("api_key") api_Key: String,
        @Query("method") method: String,
        @Query("user_id") user_Id: String,
        @Query("extras") extras: String,
        @Query("format") format: String,
        @Query("nojsoncallback") noJsonCallback: Int,
        @Query("per_page") per_Page: Int,
        @Query("text") text : String
    ): Call<PhotoJson>
}