package com.task.trendingrepos.domain.usecase

import com.task.trendingrepos.domain.model.TrendingRepo
import com.task.trendingrepos.domain.repository.ITrendingRepoSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TrendingRepositoryUseCase @Inject constructor(
    private val searchRepository: ITrendingRepoSearchRepository
) : ITrendingRepositoryUseCase {

    override suspend fun getTrendingRepositories(): Flow<List<TrendingRepo>> {
        return searchRepository.searchTrendingRepositories()
    }
}
