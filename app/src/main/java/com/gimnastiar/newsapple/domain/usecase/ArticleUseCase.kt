package com.gimnastiar.newsapple.domain.usecase

import com.gimnastiar.newsapple.data.Resource
import com.gimnastiar.newsapple.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleUseCase {
    fun getPopularArticle() : Flow<Resource<List<Article>>>

    fun getArticleHasBeenRead() : Flow<List<Article>>

    suspend fun insertArticle(article: Article)

    suspend fun deleteArticle(article: Article)
}