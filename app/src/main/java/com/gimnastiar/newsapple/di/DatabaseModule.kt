package com.gimnastiar.newsapple.di

import android.content.Context
import androidx.room.Room
import com.gimnastiar.newsapple.data.source.local.room.ArticleDao
import com.gimnastiar.newsapple.data.source.local.room.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ArticleDatabase {
        return Room.databaseBuilder(
            context,
            ArticleDatabase::class.java, "article.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providesArticleDao(database: ArticleDatabase): ArticleDao = database.articleDao()
}