package com.gimnastiar.newsapple.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class ArticlesResponse(
    @SerializedName("articles")
    val resultArticles: List<ResultArticle>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)