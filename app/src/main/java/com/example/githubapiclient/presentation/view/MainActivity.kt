package com.example.githubapiclient.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import com.example.githubapiclient.MainApplication
import com.example.githubapiclient.R
import com.example.githubapiclient.data.datasource.GitHubService
import com.example.githubapiclient.domain.repository.RepoRepository
import com.example.githubapiclient.domain.repository.UserRepository
import com.example.githubapiclient.presentation.viewmodel.MainViewModel
import com.google.android.material.textfield.TextInputEditText
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            openMainFragment()
        }
    }

    private fun openMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container_view,
                MainFragment()
            )
            .setReorderingAllowed(true)
            .commit()
    }
}
