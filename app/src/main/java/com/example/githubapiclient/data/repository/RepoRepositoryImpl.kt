package com.example.githubapiclient.data.repository

import android.util.Log
import com.example.githubapiclient.data.datasource.GitHubService
import com.example.githubapiclient.data.model.toDomain
import com.example.githubapiclient.domain.model.Repo
import com.example.githubapiclient.domain.repository.RepoRepository
import com.example.githubapiclient.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(private val gitHubService: GitHubService) : RepoRepository {
    override fun getUserRepos(username: String): Flow<Resource<List<Repo>>> = flow {
        try {
            val response = gitHubService.getUserRepos(username)
            emit(Resource.Loading())
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!.map { it.toDomain() }))
            } else {
                emit(Resource.Error(response.code().toString()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.stackTraceToString()))
        }
    }.flowOn(Dispatchers.IO)
}