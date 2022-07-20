package com.example.githubapiclient.domain.model

import java.time.LocalDateTime

data class Repo(
    val name: String?,
    val description: String?,
    val language: String?,
    val starsCount: Int?,
    val forksCount: Int?
)
