package com.devspacecinenow.di

import android.app.Application
import androidx.room.Room
import com.devspacecinenow.CineNowApplication
import com.devspacecinenow.common.data.local.CineNowDatabase
import com.devspacecinenow.common.data.local.MovieDao
import com.devspacecinenow.common.data.remote.RetrofitClient
import com.devspacecinenow.list.data.remote.ListService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class CineNowModule {

    @Provides
    fun providesCineNowDatabase(application: Application): CineNowDatabase{
        return Room.databaseBuilder(
            application.applicationContext,
            CineNowDatabase::class.java, "database-cine-now",
        ).build()
    }

    @Provides
    fun providesMovieDao(roomDatabase: CineNowDatabase): MovieDao{
        return roomDatabase.getMovieDao()
    }

    @Provides
    fun providesRetrofit(): Retrofit{
        return RetrofitClient.retrofitInstance
    }

    @Provides
    @DispatcherIO
    fun providesDispatcherIO(): CoroutineDispatcher{
        return Dispatchers.IO
    }

}