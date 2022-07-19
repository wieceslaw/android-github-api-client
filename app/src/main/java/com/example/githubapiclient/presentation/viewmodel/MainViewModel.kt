package com.example.githubapiclient.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapiclient.domain.model.User
import com.example.githubapiclient.domain.repository.UserRepository
import com.example.githubapiclient.util.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class MainViewModel: ViewModel() {
    @Inject
    lateinit var userRepository: UserRepository

    private val _successFound: MutableLiveData<Boolean> = MutableLiveData(false)
    val successFound: LiveData<Boolean> = _successFound

    private val _status: MutableLiveData<String> = MutableLiveData("")
    val status: LiveData<String> = _status

    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> = _user

    fun getUserProfile(username: String) {
        userRepository.getUserProfile(username).onEach {
            when (it) {
                is Resource.Success -> {
                    _user.value = it.data
                    _successFound.value = true
                    _status.value = "Successfully found user!"
                }
                is Resource.Loading -> {
                    _user.value = null
                    _successFound.value = false
                    _status.value = "Loading..."
                }
                is Resource.Error -> {
                    _user.value = null
                    _successFound.value = false
                    _status.value = "Error: " + it.message
                }
            }
        }.launchIn(viewModelScope)
    }
}