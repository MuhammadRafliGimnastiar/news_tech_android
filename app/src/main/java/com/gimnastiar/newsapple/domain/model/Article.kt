package com.gimnastiar.newsapple.domain.model

import android.os.Parcelable
import com.gimnastiar.newsapple.data.source.remote.response.Source
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val author: String? = "Unkwonn Author",
    val content: String? = "null",
    val description: String? = "null",
    val publishedAt: String? = "null",
    val title: String,
    val url: String? = "null",
    val urlToImage: String? = "null"
) : Parcelable
