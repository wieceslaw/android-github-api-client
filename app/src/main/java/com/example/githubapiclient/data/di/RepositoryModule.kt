package com.example.githubapiclient.data.di

import com.example.githubapiclient.data.repository.RepoRepositoryImpl
import com.example.githubapiclient.data.repository.UserRepositoryImpl
import com.example.githubapiclient.domain.repository.RepoRepository
import com.example.githubapiclient.domain.repository.UserRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule   {
    @Binds
    fun bindUserRepository(repository: UserRepositoryImpl): UserRepository

    @Binds
    fun bindRepoRepository(repository: RepoRepositoryImpl): RepoRepository
}