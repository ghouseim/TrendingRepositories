package com.task.trendingrepos.data.di

import com.task.trendingrepos.domain.usecase.ITrendingRepositoryUseCase
import com.task.trendingrepos.domain.usecase.TrendingRepositoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun getTrendingRepoUseCase(trendingRepositoryUseCase: TrendingRepositoryUseCase)
            : ITrendingRepositoryUseCase = trendingRepositoryUseCase
}
