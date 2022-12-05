package com.task.trendingrepos.data.mapper

import com.task.trendingrepos.data.model.RepoResponseItem
import com.task.trendingrepos.domain.model.TrendingRepo

fun RepoResponseItem.toDomain(): TrendingRepo {
    return TrendingRepo(
        author = this.fullName.orEmpty(),
        avatar = this.owner?.avatarUrl.orEmpty(),
        description = this.description.orEmpty(),
        language = this.language.orEmpty(),
        name = this.name.orEmpty(),
        url = this.url.orEmpty()
    )
}