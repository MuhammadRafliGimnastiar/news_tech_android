package com.gimnastiar.newsapple.ui.screen.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.gimnastiar.newsapple.R
import com.gimnastiar.newsapple.data.Resource
import com.gimnastiar.newsapple.databinding.FragmentDetailBinding
import com.gimnastiar.newsapple.domain.model.Article
import com.gimnastiar.newsapple.ui.adapter.SearchAdapter
import com.gimnastiar.newsapple.utils.Helper
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    private val viewModel: DetailViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)

        viewModel.setDataArticle(args.articleData)

        setupData()
        othersArticle()
        buttonClick()

    }

    private fun buttonClick() = with(binding) {
        topAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        btnLink.setOnClickListener {
            viewModel.article.observe(viewLifecycleOwner) {
                if (it.url != null) {
                    val action = DetailFragmentDirections.actionDetailFragmentToWebViewFragment(it.url)
                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun othersArticle() = with(binding) {
        val adapter = SearchAdapter()
        adapter.setOnItemClickCallback(object: SearchAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Article) {
                viewModel.setDataArticle(data)

                contentDetailScroll.smoothScrollTo(0, 0)
            }

        })

        rvOthersArticle.adapter = adapter
        rvOthersArticle.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.articles.observe(viewLifecycleOwner) { article ->
            if (article != null) {
                when (article) {
                    is Resource.Loading -> {
                        isLoading(true)
                    }

                    is Resource.Success -> {
                        isLoading(false)
                        article.data.let { dataArticle ->
                            if (dataArticle != null) {
                                val newArticleList = dataArticle.filter { it.author != args.articleData.author }
                                adapter.submitList(newArticleList.shuffled())
                            }
                        }
                    }

                    is Resource.Error -> {
                        isLoading(true)
                        val message = article.message
                        Toast.makeText(requireContext(), "$message, other articles cannot be displayed", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }

    private fun isLoading(loading: Boolean) = with(binding) {
        if(loading) {
            shimmer.visibility = View.VISIBLE
            rvOthersArticle.visibility = View.GONE
        } else {
            shimmer.visibility = View.GONE
            rvOthersArticle.visibility = View.VISIBLE
        }
    }

    private fun setupData() = with(binding) {
        viewModel.article.observe(viewLifecycleOwner) { data ->
            tvTitle.text = data.title
            Glide.with(requireContext())
                .load(data.urlToImage)
                .into(imgArticle)
            tvAuthor.text = data.author
            tvTimeStamp.text = Helper.formatDate(data.publishedAt.toString())
            tvDescription.text = data.description
        }
    }


}