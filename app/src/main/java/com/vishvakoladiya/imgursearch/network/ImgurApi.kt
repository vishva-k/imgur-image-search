package com.vishvakoladiya.imgursearch.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ImgurApi {

    // search imgur gallery
    @GET("3/gallery/search")
    suspend fun searchImages(
        @Query("q") query: String,
        @Query("page") page: Int = 0
    ): ImgurResponse
}