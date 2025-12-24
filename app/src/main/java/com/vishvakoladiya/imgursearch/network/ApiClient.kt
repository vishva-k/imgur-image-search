package com.vishvakoladiya.imgursearch.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    // base URL for imgur API request
    private const val BASE_URL = "https://api.imgur.com/"

    // client ID for authorization
    private const val CLIENT_ID = "b067d5cb828ec5a"

    // adds auth header to every request
    private val authInterceptor = Interceptor { chain ->
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Client-ID $CLIENT_ID")
            .build()
        chain.proceed(request)
    }

    // OkHttp client
    private val client = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    // access imgur endpoints
    val imgurApi: ImgurApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ImgurApi::class.java)
}