package com.example.githubapiclient.domain.repository

import com.example.githubapiclient.domain.model.User
import com.example.githubapiclient.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserProfile(username: String): Flow<Resource<User>>
}