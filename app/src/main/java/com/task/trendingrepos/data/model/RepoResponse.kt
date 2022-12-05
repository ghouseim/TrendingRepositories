package com.task.trendingrepos.data.model


import com.google.gson.annotations.SerializedName

data class RepoResponse(
    @SerializedName("total_count")
    val totalCount: Int?,
    @SerializedName("items")
    val items: List<RepoResponseItem>
)

data class RepoResponseItem(
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("owner")
    val owner: Owner?,
    @SerializedName("private")
    val `private`: Boolean?,
    @SerializedName("url")
    val url: String?
) {
    data class Owner(
        @SerializedName("avatar_url")
        val avatarUrl: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("type")
        val type: String?,
        @SerializedName("url")
        val url: String?
    )
}