package com.example.githubapiclient.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapiclient.domain.repository.RepoRepository
import com.example.githubapiclient.util.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class DetailsViewModel : ViewModel() {
    @Inject
    lateinit var repoRepository: RepoRepository

    private val _userRepos: MutableLiveData<String> = MutableLiveData("")
    val userRepos: LiveData<String> = _userRepos

    fun getUserRepos(username: String) {
        repoRepository.getUserRepos(username).onEach {
            when (it) {
                is Resource.Success -> {
                    _userRepos.value = it.data!!.toString()
                }
                is Resource.Loading -> {
                    _userRepos.value = "loading"
                }
                is Resource.Error -> {
                    _userRepos.value = it.message
                }
            }
        }.launchIn(viewModelScope)
    }
}