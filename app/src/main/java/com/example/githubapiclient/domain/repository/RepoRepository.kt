package com.example.githubapiclient.domain.repository

import com.example.githubapiclient.domain.model.Repo
import com.example.githubapiclient.util.Resource
import kotlinx.coroutines.flow.Flow

interface RepoRepository {
    fun getUserRepos(username: String): Flow<Resource<List<Repo>>>
}