package com.gimnastiar.newsapple.data.source.local

import com.gimnastiar.newsapple.data.source.local.entity.ArticleEntiry
import com.gimnastiar.newsapple.data.source.local.room.ArticleDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val dao: ArticleDao
) {
    fun getHasBeenReadArticle(): Flow<List<ArticleEntiry>> = dao.getHasBeenReadArticle()

    suspend fun insertArticle(article: ArticleEntiry) = dao.insertArticleRead(article)

    suspend fun deleteArticle(article: ArticleEntiry) = dao.deleteArticleRead(article)
}