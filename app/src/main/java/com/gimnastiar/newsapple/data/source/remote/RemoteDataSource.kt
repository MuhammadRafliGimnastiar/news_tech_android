package com.gimnastiar.newsapple.data.source.remote

import android.util.Log
import com.gimnastiar.newsapple.BuildConfig
import com.gimnastiar.newsapple.data.source.remote.network.ApiResponse
import com.gimnastiar.newsapple.data.source.remote.network.ApiService
import com.gimnastiar.newsapple.data.source.remote.response.ResultArticle
import com.gimnastiar.newsapple.utils.Helper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getListArticle(): Flow<ApiResponse<List<ResultArticle>>> {
        return flow {
            try {
                val response =
                    apiService.getListPopular(
                        page = Random.nextInt(1, 5),
                        pageSize = 15,
                        from = Helper.getRandomDate(),
                        apiKey = BuildConfig.API_KEY
                    )
                val result = response.resultArticles
                if (result.isNotEmpty()) {
                    emit(ApiResponse.Success(response.resultArticles))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}