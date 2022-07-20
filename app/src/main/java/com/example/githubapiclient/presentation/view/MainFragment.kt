package com.example.githubapiclient.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.githubapiclient.MainApplication
import com.example.githubapiclient.R
import com.example.githubapiclient.presentation.viewmodel.MainViewModel
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso

class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MainApplication).applicationComponent.injectMainViewModel(
            viewModel
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val findUserButton = requireView().findViewById<Button>(R.id.profile_button)
        val userProfileTextView = requireView().findViewById<TextView>(R.id.user_profile_text_view)
        val textEdit = requireView().findViewById<TextInputEditText>(R.id.edit_text)
        val detailsButton = requireView().findViewById<Button>(R.id.details_button)
        val imageView = requireView().findViewById<ImageView>(R.id.image_view)
        val statusTextView = requireView().findViewById<TextView>(R.id.status_text_view)

        findUserButton.setOnClickListener {
            viewModel.getUserProfile(textEdit.text.toString())
        }

        detailsButton.setOnClickListener {
            openDetailsFragment()
        }

        viewModel.status.observe(viewLifecycleOwner) {
            statusTextView.text = it
        }

        viewModel.user.observe(viewLifecycleOwner) {
            if (it != null) {
                Picasso.get()
                    .load(it.avatarUrl)
                    .placeholder(R.drawable.loading_placeholder)
                    .into(imageView)
                userProfileTextView.text = it.login
            } else {
                imageView.setImageResource(android.R.color.transparent);
                userProfileTextView.text = ""
            }
        }

        viewModel.successFound.observe(viewLifecycleOwner) {
            if (it) {
                detailsButton.visibility = View.VISIBLE
            } else {
                detailsButton.visibility = View.INVISIBLE
            }
        }
    }


    private fun openDetailsFragment() {
        parentFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(
                R.id.fragment_container_view,
                DetailsFragment.newInstance(viewModel.user.value!!.login)
            )
            .setReorderingAllowed(true)
            .commit()
    }
}
