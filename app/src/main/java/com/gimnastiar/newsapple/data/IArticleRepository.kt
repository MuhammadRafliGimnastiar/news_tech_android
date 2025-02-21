package com.gimnastiar.newsapple.data

import com.gimnastiar.newsapple.data.source.local.LocalDataSource
import com.gimnastiar.newsapple.data.source.remote.RemoteDataSource
import com.gimnastiar.newsapple.data.source.remote.network.ApiResponse
import com.gimnastiar.newsapple.data.source.remote.response.ResultArticle
import com.gimnastiar.newsapple.domain.model.Article
import com.gimnastiar.newsapple.domain.repository.ArticleRepository
import com.gimnastiar.newsapple.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IArticleRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ArticleRepository{
    override fun getPopularArticle(): Flow<Resource<List<Article>>> =
        object : NetworkBoundResource<List<Article>, List<ResultArticle>>() {
            override fun loadFromNetwork(data: List<ResultArticle>): Flow<List<Article>> =
                DataMapper.mapResponseToDomain(data)

            override suspend fun createCall(): Flow<ApiResponse<List<ResultArticle>>> =
                remoteDataSource.getListArticle()

        }.asFlow()

    override fun getArticleHasBeenRead(): Flow<List<Article>> =
        localDataSource.getHasBeenReadArticle().map {
            DataMapper.mapEntityToDomain(it)
        }

    override suspend fun insertArticleHasBeenRead(article: Article) =
        localDataSource.insertArticle(DataMapper.mapDomainToEntity(article))

    override suspend fun deleteHistoryArticle(article: Article) =
        localDataSource.deleteArticle(DataMapper.mapDomainToEntity(article))
}