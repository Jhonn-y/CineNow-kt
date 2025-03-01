package com.devspacecinenow

import android.app.Application
import androidx.room.Room
import com.devspacecinenow.common.data.remote.RetrofitClient
import com.devspacecinenow.common.data.local.CineNowDatabase
import com.devspacecinenow.list.data.MovieListRepo
import com.devspacecinenow.list.data.local.MovieListLocalDataSouce
import com.devspacecinenow.list.data.remote.ListService
import com.devspacecinenow.list.data.remote.MovieListRemoteDataSouce

class CineNowApplication : Application() {
    val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            CineNowDatabase::class.java, "database-cine-now",
        ).build()
    }

    private val listService by lazy {
        RetrofitClient.retrofitInstance.create(ListService::class.java)
    }

    private val localDataSource: MovieListLocalDataSouce by lazy {
        MovieListLocalDataSouce(db.getMovieDao())
    }

    private val remoteDataSource: MovieListRemoteDataSouce by lazy {
        MovieListRemoteDataSouce(listService)
    }

    val repository: MovieListRepo by lazy {
        MovieListRepo(
            local = localDataSource,
            remote = remoteDataSource,
        )
    }
}