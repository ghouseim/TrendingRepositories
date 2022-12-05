package com.task.trendingrepos.domain.usecase

import com.task.trendingrepos.domain.model.TrendingRepo
import kotlinx.coroutines.flow.Flow

interface ITrendingRepositoryUseCase {
    suspend fun getTrendingRepositories(): Flow<List<TrendingRepo>>
}