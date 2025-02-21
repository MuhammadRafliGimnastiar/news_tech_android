package com.gimnastiar.newsapple.ui.screen.history

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gimnastiar.newsapple.R
import com.gimnastiar.newsapple.databinding.FragmentHistoryBinding
import com.gimnastiar.newsapple.domain.model.Article
import com.gimnastiar.newsapple.ui.adapter.ArticleHistoryAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment(R.layout.fragment_history) {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HistoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHistoryBinding.bind(view)

        setupData()
        buttonClick()
    }

    private fun buttonClick() {
        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupData() = with(binding) {
        val adapter = ArticleHistoryAdapter()
        adapter.setOnItemClickCallback(object: ArticleHistoryAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Article) {
                val action = HistoryFragmentDirections.actionHistoryFragmentToDetailFragment(data)
                findNavController().navigate(action)
            }

            override fun onBtnLinkClicked(url: String) {
                val action = HistoryFragmentDirections.actionHistoryFragmentToWebViewFragment(url)
                findNavController().navigate(action)
            }

            override fun onBtnRemoveCLicked(data: Article) {

                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(resources.getString(R.string.remove_question))
                    .setMessage(resources.getString(R.string.make_sure_remove_question))
                    .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                        dialog.dismiss()
                    }
                    .setPositiveButton(resources.getString(R.string.remove)) { dialog, which ->
                        viewModel.deleteFromHistory(data)
                        Toast.makeText(requireContext(), "Data has been removed", Toast.LENGTH_SHORT).show()
                    }
                    .show()
            }

        })

        rvHistory.layoutManager = LinearLayoutManager(requireContext())
        rvHistory.adapter = adapter

        viewModel.articles.observe(viewLifecycleOwner) {
            adapter.submitList(it.reversed())
        }

    }
}