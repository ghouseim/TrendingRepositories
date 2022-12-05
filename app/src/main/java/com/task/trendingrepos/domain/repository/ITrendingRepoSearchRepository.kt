package com.task.trendingrepos.domain.repository

import com.task.trendingrepos.domain.model.TrendingRepo
import kotlinx.coroutines.flow.Flow

interface ITrendingRepoSearchRepository {
    suspend fun searchTrendingRepositories(): Flow<List<TrendingRepo>>
}