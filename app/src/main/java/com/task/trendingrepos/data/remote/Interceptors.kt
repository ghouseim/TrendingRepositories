package com.task.trendingrepos.data.remote

import okhttp3.logging.HttpLoggingInterceptor

object Interceptors {

    fun createLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}
