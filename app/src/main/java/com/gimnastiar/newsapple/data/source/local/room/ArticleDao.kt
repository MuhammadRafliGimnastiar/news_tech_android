package com.gimnastiar.newsapple.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gimnastiar.newsapple.data.source.local.entity.ArticleEntiry
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article")
    fun getHasBeenReadArticle() : Flow<List<ArticleEntiry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticleRead(movie: ArticleEntiry)

    @Delete
    suspend fun deleteArticleRead(movie: ArticleEntiry)
}