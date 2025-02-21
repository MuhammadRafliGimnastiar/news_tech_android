package com.gimnastiar.newsapple.ui.screen.detail.webview

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gimnastiar.newsapple.R
import com.gimnastiar.newsapple.databinding.FragmentWebViewBinding

class WebViewFragment : Fragment(R.layout.fragment_web_view) {

    private var _binding : FragmentWebViewBinding? = null
    private val binding get() = _binding!!

    private val args: WebViewFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWebViewBinding.bind(view)


        binding.webview.loadUrl(args.urlData)
        buttonClick()
    }

    private fun buttonClick() = with(binding) {
        topAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

    }

}