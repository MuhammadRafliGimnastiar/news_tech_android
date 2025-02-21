package com.gimnastiar.newsapple.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.gimnastiar.newsapple.domain.model.Article
import com.gimnastiar.newsapple.domain.usecase.ArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val articleUseCase: ArticleUseCase): ViewModel() {

    val articles = articleUseCase.getPopularArticle().asLiveData()

    fun insertToHistory(data: Article) {
        viewModelScope.launch {
            articleUseCase.insertArticle(data)
        }
    }
}