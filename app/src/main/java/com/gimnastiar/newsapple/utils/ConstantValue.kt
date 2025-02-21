package com.gimnastiar.newsapple.utils

import com.gimnastiar.newsapple.BuildConfig

object ConstantValue {
    const val BASE_URL = BuildConfig.API_URL
    const val API_KEY = BuildConfig.API_KEY

    const val PATH_BASE = "${BASE_URL}everything"
    const val PATH_POPULAR_ARTICLES = "${BASE_URL}everything?q=apple&from=2025-02-17&sortBy=popularity&page=1&pageSize=10&apiKey=$API_KEY"
    const val PATH_ARTICLES = "${BASE_URL}everything?q=apple&from=2025-02-17&sortBy=popularity&page={page}&pageSize={page_size}&apiKey=$API_KEY"

    const val ARTICLE_ID = "article"
}