package com.example.githubapiclient.data.repository

import android.util.Log
import com.example.githubapiclient.data.datasource.GitHubService
import com.example.githubapiclient.data.model.toDomain
import com.example.githubapiclient.domain.model.User
import com.example.githubapiclient.domain.repository.UserRepository
import com.example.githubapiclient.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val gitHubService: GitHubService) : UserRepository {
    override fun getUserProfile(username: String): Flow<Resource<User>> = flow {
        try {
            val response = gitHubService.getUserProfile(username)
            emit(Resource.Loading())
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!.toDomain()))
            } else {
                when (response.code()) {
                    404 -> emit(Resource.Error("User not found"))
                    else -> emit(Resource.Error(response.code().toString()))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.stackTraceToString()))
        }
    }.flowOn(Dispatchers.IO)
}