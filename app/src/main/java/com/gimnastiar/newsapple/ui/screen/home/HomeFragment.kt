package com.gimnastiar.newsapple.ui.screen.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.gimnastiar.newsapple.R
import com.gimnastiar.newsapple.data.Resource
import com.gimnastiar.newsapple.databinding.FragmentHomeBinding
import com.gimnastiar.newsapple.domain.model.Article
import com.gimnastiar.newsapple.ui.adapter.ArticleListAdapter
import com.gimnastiar.newsapple.ui.adapter.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var articleList: ArrayList<Article>

    private var doubleBackPressed = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        setupData()
        buttonClick()
        backAction()
    }

    private fun backAction() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                with(binding) {
                    if (searchView.isShowing) {
                        searchView.hide()

                    } else if (doubleBackPressed) {
                        requireActivity().finish()
                    } else {
                        doubleBackPressed = true
                        Toast.makeText(requireContext(), "press again to exit", Toast.LENGTH_SHORT).show()

                        Handler(Looper.getMainLooper()).postDelayed({
                            doubleBackPressed = false
                        }, 2000)
                    }
                }
            }
        })
    }

    private fun buttonClick() = with(binding) {
        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.history -> {
                    findNavController().navigate(R.id.action_homeFragment_to_historyFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun setupData() = with(binding) {
        val adapter = ArticleListAdapter()
        adapter.setOnItemClickCallback(object: ArticleListAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Article) {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(data)
                findNavController().navigate(action)

                //insert history
                viewModel.insertToHistory(data)
            }
        })

        val adapterSearch = SearchAdapter()
        adapterSearch.setOnItemClickCallback(object: SearchAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Article) {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(data)
                findNavController().navigate(action)

                //insert history
                viewModel.insertToHistory(data)
            }

        })

        val layoutManager = GridLayoutManager(requireContext(), 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position % 5 == 0) 2 else 1
            }
        }

        rvArticle.layoutManager = layoutManager
        rvArticle.adapter = adapter

        rvSearch.adapter = adapterSearch
        rvSearch.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.articles.observe(viewLifecycleOwner) { article ->
            if (article != null) {
                when (article) {
                    is Resource.Loading -> {
                        isLoading(true)
                    }

                    is Resource.Success -> {
                        isLoading(false)
                        article.data.let { dataArticle ->
                            adapter.submitList(dataArticle!!.shuffled())
                            articleList = dataArticle as ArrayList
                        }
                    }

                    is Resource.Error -> {
                        isLoading(true)
                        val message = article.message
                        Toast.makeText(requireContext(), "$message, come back later..", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

        searchView.editText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val text = s?.toString()

                val filteredList: ArrayList<Article> = ArrayList()
                if (text != null)
                    for (item in articleList) {
                        if (item.title.lowercase(Locale.getDefault())
                                .contains(text.lowercase(Locale.getDefault()))
                        ) filteredList.add(item)
                    }

                if (filteredList.isEmpty()) {
                    Toast.makeText(requireContext(), "No Data Found..", Toast.LENGTH_SHORT).show()
                } else {
                    adapterSearch.filterList(filteredList)
                }
            }

            override fun afterTextChanged(s: Editable?) {}

        })

    }

    private fun isLoading(loading: Boolean) = with(binding) {
        if(loading) {
            shimmer.visibility = View.VISIBLE
            tvNews.visibility = View.GONE
            rvArticle.visibility = View.GONE
        } else {
            shimmer.visibility = View.GONE
            tvNews.visibility = View.VISIBLE
            rvArticle.visibility = View.VISIBLE
        }
    }

}