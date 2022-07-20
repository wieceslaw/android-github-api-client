package com.example.githubapiclient.data.model

import com.example.githubapiclient.domain.model.Repo
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RepoDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("stargazers_count")
    val starsCount: Int,
    @SerializedName("forks_count")
    val forksCount: Int
)

inline fun formatDateTime(dateStr: String): LocalDateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ssZ"))

fun RepoDTO.toDomain() = Repo(
    name,
    description,
//    formatDateTime(updatedAt),
    language,
    starsCount,
    forksCount
)