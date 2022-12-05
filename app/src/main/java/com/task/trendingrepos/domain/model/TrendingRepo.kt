package com.task.trendingrepos.domain.model


data class TrendingRepo(
    val author: String,
    val avatar: String,
    val description: String,
    val language: String? = null,
    val languageColor: String? = null,
    val name: String,
    val stars: Int = 0,
    val url: String
) {
    companion object {
        val default = TrendingRepo(
            author = "",
            avatar = "",
            description = "",
            name = "",
            stars = 0,
            url = ""
        )
    }
}
