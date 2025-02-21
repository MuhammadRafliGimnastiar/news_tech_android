package com.gimnastiar.newsapple.utils

import androidx.paging.PagingData
import androidx.paging.map
import com.gimnastiar.newsapple.data.source.local.entity.ArticleEntiry
import com.gimnastiar.newsapple.data.source.remote.response.ResultArticle
import com.gimnastiar.newsapple.domain.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object DataMapper {
    fun mapResponseToDomain(input: List<ResultArticle>) : Flow<List<Article>> {
        val listArticle = ArrayList<Article>()
        input.map {
            val article = Article(
                it.author,
                it.content,
                it.description,
                it.publishedAt,
                it.title,
                it.url,
                it.urlToImage
            )
            listArticle.add(article)
        }
        return flowOf(listArticle)
    }

    fun mapResponseToDomainPaging(input: PagingData<ResultArticle>): Flow<PagingData<Article>> {
        return flowOf(
            input.map { resultArticle ->
                Article(
                    resultArticle.author,
                    resultArticle.content,
                    resultArticle.description,
                    resultArticle.publishedAt,
                    resultArticle.title,
                    resultArticle.url,
                    resultArticle.urlToImage
                )
            }
        )
    }

    fun mapEntityToDomain(input: List<ArticleEntiry>) : List<Article> {
        val listArticle = ArrayList<Article>()
        input.map {
            val article = Article(
                it.author,
                it.content,
                it.description,
                it.publishedAt,
                it.title,
                it.url,
                it.urlToImage
            )
            listArticle.add(article)
        }
        return listArticle
    }

    fun mapDomainToEntity(it: Article) : ArticleEntiry {
        return ArticleEntiry(
            it.author,
            it.content,
            it.description,
            it.publishedAt,
            it.title,
            it.url,
            it.urlToImage
        )

    }
}