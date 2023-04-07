package com.example.sampleapp.api

import com.example.sampleapp.model.CountryModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/")
    suspend fun getCountryData(@Query("name") name: String): Response<CountryModel>
}