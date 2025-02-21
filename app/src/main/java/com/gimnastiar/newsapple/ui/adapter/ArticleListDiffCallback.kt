package com.gimnastiar.newsapple.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.gimnastiar.newsapple.domain.model.Article

class ArticleListDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}