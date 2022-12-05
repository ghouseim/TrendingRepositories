package com.task.trendingrepos.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.task.trendingrepos.domain.model.TrendingRepo
import com.task.trendingrepos.domain.usecase.ITrendingRepositoryUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val dispatcherRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var trendingRepositoryUseCase: ITrendingRepositoryUseCase

    lateinit var mainViewModel: MainViewModel


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(trendingRepositoryUseCase)
    }

    @Test
    fun `Given when fetched trending repo then return success`() {
        coEvery { trendingRepositoryUseCase.getTrendingRepositories() } returns flow {
            emit(listOf(TrendingRepo.default))
        }
        mainViewModel.fetchTrendingRepos()

        val result = mainViewModel.trendingRepoData

        Assert.assertNotNull(result)
    }
}