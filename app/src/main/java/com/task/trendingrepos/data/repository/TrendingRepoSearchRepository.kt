package com.task.trendingrepos.data.repository

import com.task.trendingrepos.data.mapper.toDomain
import com.task.trendingrepos.data.remote.TrendingRepoApiService
import com.task.trendingrepos.domain.model.TrendingRepo
import com.task.trendingrepos.domain.repository.ITrendingRepoSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class TrendingRepoSearchRepository @Inject constructor(
    private val trendingRepo: TrendingRepoApiService
) : ITrendingRepoSearchRepository {

    override suspend fun searchTrendingRepositories(): Flow<List<TrendingRepo>> {
        return flow {
            try {
                val response = trendingRepo.trendingRepositories()
                val data = response.items.map { result -> result.toDomain() }
                emit(data)
            } catch (e: Exception) {
                throw e
            }
        }
    }
}
