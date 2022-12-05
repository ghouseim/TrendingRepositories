package com.task.trendingrepos.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TrendingRepositoryUiModel(
    val author: String,
    val avatar: String,
    val description: String,
    val forks: Int,
    val language: String? = null,
    val languageColor: String? = null,
    val name: String,
    val stars: Int,
    val url: String,
    var isSelected: Boolean = false
) : Parcelable {

    companion object {
        val default = TrendingRepositoryUiModel(
            author = "",
            avatar = "",
            description = "",
            forks = 0,
            name = "",
            stars = 0,
            url = ""
        )
    }
}
