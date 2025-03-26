package com.devspacecinenow.detail.di

import com.devspacecinenow.detail.data.DetailService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
class MovieDetailModule {

    @Provides
    fun providesMovieDetailService(retrofit: Retrofit): DetailService{
        return retrofit.create(DetailService::class.java)
    }
}