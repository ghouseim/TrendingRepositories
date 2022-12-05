package com.task.trendingrepos.data.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.task.trendingrepos.data.remote.HttpClient
import com.task.trendingrepos.data.remote.Interceptors
import com.task.trendingrepos.data.remote.TrendingRepoApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
open class DataModule {

    @Provides
    open fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        Interceptors.createLoggingInterceptor()

    @Provides
    open fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return HttpClient.setupOkhttpClient(httpLoggingInterceptor)
    }

    @Singleton
    @Provides
    open fun provideTrendingRepoApi(retrofit: Retrofit): TrendingRepoApiService =
        retrofit.create(TrendingRepoApiService::class.java)

    @Singleton
    @Provides
    open fun provideRetorfit(
        okHttpClient: OkHttpClient,
        @Named("baseUrl") baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Named("baseUrl")
    open fun provideBaseUrl() = "https://api.github.com/search/"
}
