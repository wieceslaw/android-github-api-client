package com.example.githubapiclient.data.datasource

import com.example.githubapiclient.data.model.RepoDTO
import com.example.githubapiclient.data.model.UserDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{username}")
    suspend fun getUserProfile(@Path("username") username: String): Response<UserDTO>

    @GET("users/{username}/repos")
    suspend fun getUserRepos(@Path("username") username: String): Response<List<RepoDTO>>
}