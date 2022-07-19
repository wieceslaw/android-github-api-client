package com.example.githubapiclient.data.model

import com.example.githubapiclient.domain.model.User
import com.google.gson.annotations.SerializedName

class UserDTO(
    @SerializedName("login")
    val login: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("followers_url")
    val followersUrl: String,
    @SerializedName("following_url")
    val followingUrl: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("bio")
    val bio: String,
    @SerializedName("created_at")
    val createdAt: String
)

fun UserDTO.toDomain() = User(
    login, avatarUrl
)
