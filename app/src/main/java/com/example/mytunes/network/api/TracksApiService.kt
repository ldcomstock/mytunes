package com.example.mytunes.network.api

import com.example.mytunes.network.model.SearchResultsDomainObject
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://itunes.apple.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface TracksApiService {
    @GET("search")
    suspend fun searchTracks(
        @Query("term") searchTerm: CharSequence?
    ): SearchResultsDomainObject
}

object TracksApi {
    val retrofitService : TracksApiService by lazy {
        retrofit.create(TracksApiService::class.java) }
}