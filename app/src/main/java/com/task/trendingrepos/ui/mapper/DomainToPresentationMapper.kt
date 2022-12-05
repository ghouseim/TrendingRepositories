package com.task.trendingrepos.ui.mapper

import com.task.trendingrepos.ui.model.TrendingRepositoryUiModel
import com.task.trendingrepos.domain.model.TrendingRepo


fun TrendingRepo.toPresentation(): TrendingRepositoryUiModel {
    return TrendingRepositoryUiModel(
        this.author,
        this.avatar,
        this.description,
        0,
        this.language,
        this.languageColor,
        this.name,
        this.stars,
        this.url,
        false
    )
}