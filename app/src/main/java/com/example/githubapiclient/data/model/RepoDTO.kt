package com.example.githubapiclient.data.model

import com.example.githubapiclient.domain.model.Repo
import com.google.gson.annotations.SerializedName

class RepoDTO(
    @SerializedName("name")
    val name: String
)

fun RepoDTO.toDomain() = Repo(
    name
)