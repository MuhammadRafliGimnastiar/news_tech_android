package com.gimnastiar.newsapple.data.source.remote.network

import com.gimnastiar.newsapple.data.source.remote.response.ArticlesResponse
import com.gimnastiar.newsapple.utils.ConstantValue
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(ConstantValue.PATH_BASE)
    suspend fun getListPopular(
        @Query("q") query: String = "apple",
        @Query("from") from: String,
        @Query("sortBy") sortBy: String = "popularity",
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String
    ) : ArticlesResponse
}