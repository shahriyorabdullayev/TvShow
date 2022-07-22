package com.shahriyor.android_imperative.data.remote.service

import com.shahriyor.android_imperative.model.TVShowDetails
import com.shahriyor.android_imperative.model.TVShowPopular
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowService {

    @GET("api/most-popular")
    suspend fun apiTVShowPopular(@Query("page") page: Int): Response<TVShowPopular>

    @GET("api/show-details")
    suspend fun apiTVShowDetails(@Query("q") q: Int): Response<TVShowDetails>
}