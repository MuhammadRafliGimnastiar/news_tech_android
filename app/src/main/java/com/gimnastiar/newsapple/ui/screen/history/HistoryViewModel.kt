package com.gimnastiar.newsapple.ui.screen.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.gimnastiar.newsapple.domain.model.Article
import com.gimnastiar.newsapple.domain.usecase.ArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val articleUseCase: ArticleUseCase): ViewModel() {

    val articles = articleUseCase.getArticleHasBeenRead().asLiveData()

    fun deleteFromHistory(data: Article) {
        viewModelScope.launch {
            articleUseCase.deleteArticle(data)
        }
    }
}