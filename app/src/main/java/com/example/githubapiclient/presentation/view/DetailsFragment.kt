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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapiclient.MainApplication
import com.example.githubapiclient.R
import com.example.githubapiclient.presentation.viewmodel.DetailsViewModel
import com.example.githubapiclient.presentation.viewmodel.MainViewModel

class DetailsFragment : Fragment(R.layout.fragment_details) {
    private var username: String? = null
    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MainApplication).applicationComponent.injectDetailsViewModel(
            viewModel
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(ARG_PARAM_USERNAME)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        username?.let { viewModel.getUserRepos(it) }

        recyclerView = requireView().findViewById(R.id.recycler_view)
        val adapter = ReposRecyclerAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        viewModel.userRepos.observe(viewLifecycleOwner) {
            it?.let {
                adapter.updateReposList(it)
            }
        }
    }

    companion object {
        private const val ARG_PARAM_USERNAME = "username"
        @JvmStatic
        fun newInstance(username: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_USERNAME, username)
                }
            }
    }
}