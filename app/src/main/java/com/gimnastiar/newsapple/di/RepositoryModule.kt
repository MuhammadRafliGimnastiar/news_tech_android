package com.gimnastiar.newsapple.di

import com.gimnastiar.newsapple.data.IArticleRepository
import com.gimnastiar.newsapple.domain.repository.ArticleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(tourismRepository: IArticleRepository): ArticleRepository
}