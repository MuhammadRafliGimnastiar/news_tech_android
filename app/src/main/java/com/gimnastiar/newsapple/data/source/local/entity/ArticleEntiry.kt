package com.gimnastiar.newsapple.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class ArticleEntiry(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    @PrimaryKey
    val title: String,
    val url: String?,
    val urlToImage: String?
)