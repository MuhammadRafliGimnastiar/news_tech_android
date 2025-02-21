package com.gimnastiar.newsapple.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gimnastiar.newsapple.data.source.local.entity.ArticleEntiry

@Database(entities = [ArticleEntiry::class], version = 1, exportSchema = false)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao() : ArticleDao
}