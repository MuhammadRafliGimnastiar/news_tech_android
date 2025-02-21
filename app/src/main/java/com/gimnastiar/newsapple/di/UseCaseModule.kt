package com.gimnastiar.newsapple.di

import com.gimnastiar.newsapple.domain.usecase.ArticleInteractor
import com.gimnastiar.newsapple.domain.usecase.ArticleUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun provideArticleUseCase(articleInteractor: ArticleInteractor) : ArticleUseCase
}