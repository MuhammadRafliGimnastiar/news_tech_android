package com.gimnastiar.newsapple.domain.repository

import com.gimnastiar.newsapple.data.Resource
import com.gimnastiar.newsapple.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    fun getPopularArticle() : Flow<Resource<List<Article>>>

    fun getArticleHasBeenRead() : Flow<List<Article>>

    suspend fun insertArticleHasBeenRead(article: Article)

    suspend fun deleteHistoryArticle(article: Article)
}