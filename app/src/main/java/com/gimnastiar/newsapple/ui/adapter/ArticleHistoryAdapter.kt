package com.gimnastiar.newsapple.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gimnastiar.newsapple.databinding.ItemArticleHistoryBinding
import com.gimnastiar.newsapple.domain.model.Article

class ArticleHistoryAdapter :
    ListAdapter<Article, ArticleHistoryAdapter.ListViewHolder>(ArticleListDiffCallback()) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(var binding: ItemArticleHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Article) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.urlToImage)
                    .into(imgPoster)
                tvTitle.text = data.title
                tvDescription.text = data.description
                btnLink.setOnClickListener {
                    if (data.url != null)
                        onItemClickCallback.onBtnLinkClicked(data.url)
                }
                btnRemove.setOnClickListener { onItemClickCallback.onBtnRemoveCLicked(data) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemArticleHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(data) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Article)
        fun onBtnLinkClicked(url: String)
        fun onBtnRemoveCLicked(data: Article)
    }
}