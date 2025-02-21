package com.gimnastiar.newsapple.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gimnastiar.newsapple.databinding.ItemArticleLargeBinding
import com.gimnastiar.newsapple.domain.model.Article

class ArticleListAdapter :
    ListAdapter<Article, ArticleListAdapter.ListViewHolder>(ArticleListDiffCallback()) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(var binding: ItemArticleLargeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Article) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.urlToImage)
                    .into(imgArticle)
                tvTitle.text = data.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemArticleLargeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(data) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Article)
    }
}