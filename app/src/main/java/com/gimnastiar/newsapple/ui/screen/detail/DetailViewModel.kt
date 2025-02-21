package com.gimnastiar.newsapple.ui.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.gimnastiar.newsapple.domain.model.Article
import com.gimnastiar.newsapple.domain.usecase.ArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val articleUseCase: ArticleUseCase): ViewModel() {

    private val _article = MutableLiveData<Article>()
    val article: LiveData<Article> get() = _article

    fun setDataArticle(data: Article) {
        _article.value = data
        viewModelScope.launch {
            articleUseCase.insertArticle(data)
        }
    }

    val articles = articleUseCase.getPopularArticle().asLiveData()

}