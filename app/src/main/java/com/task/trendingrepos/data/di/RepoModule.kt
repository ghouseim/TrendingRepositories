package com.task.trendingrepos.data.di

import com.task.trendingrepos.data.repository.TrendingRepoSearchRepository
import com.task.trendingrepos.domain.repository.ITrendingRepoSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    fun provideTrendingRepo(
        trendingRepoSearchRepository: TrendingRepoSearchRepository
    ): ITrendingRepoSearchRepository {
        return trendingRepoSearchRepository
    }
}
