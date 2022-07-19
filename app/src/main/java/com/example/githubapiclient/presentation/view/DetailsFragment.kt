package com.example.githubapiclient.presentation.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.githubapiclient.MainApplication
import com.example.githubapiclient.R
import com.example.githubapiclient.presentation.viewmodel.DetailsViewModel
import com.example.githubapiclient.presentation.viewmodel.MainViewModel

class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val viewModel: DetailsViewModel by viewModels()
    lateinit var textView: TextView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MainApplication).applicationComponent.injectDetailsViewModel(
            viewModel
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserRepos("wieceslaw")

        textView = requireView().findViewById(R.id.text_view)

        viewModel.userRepos.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
    }
}