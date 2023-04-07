package com.example.sampleapp.instance

import com.example.sampleapp.api.Api
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object Instance {
    fun getInstance(): Api {
        return Retrofit.Builder().baseUrl("https://api.nationalize.io")
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build().create(Api::class.java)
    }
}