package com.task.trendingrepos.data.remote

import com.task.trendingrepos.data.model.RepoResponse
import retrofit2.http.GET


interface TrendingRepoApiService {

    @GET("repositories?q=trending&sort=stars&order+desc")
    suspend fun trendingRepositories(): RepoResponse
}