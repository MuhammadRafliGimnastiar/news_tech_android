package com.gimnastiar.newsapple.domain.usecase

import com.gimnastiar.newsapple.data.Resource
import com.gimnastiar.newsapple.domain.model.Article
import com.gimnastiar.newsapple.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticleInteractor @Inject constructor(
    private val repository: ArticleRepository
): ArticleUseCase {
    override fun getPopularArticle(): Flow<Resource<List<Article>>> =
        repository.getPopularArticle()

    override fun getArticleHasBeenRead(): Flow<List<Article>> =
        repository.getArticleHasBeenRead()

    override suspend fun insertArticle(article: Article) =
        repository.insertArticleHasBeenRead(article)

    override suspend fun deleteArticle(article: Article) =
        repository.deleteHistoryArticle(article)
}