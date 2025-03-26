package com.devspacecinenow

import android.app.Application
import androidx.room.Room
import com.devspacecinenow.common.data.local.CineNowDatabase
import com.devspacecinenow.common.data.remote.RetrofitClient
import com.devspacecinenow.list.data.MovieListRepo
import com.devspacecinenow.list.data.local.MovieListLocalDataSource
import com.devspacecinenow.list.data.remote.ListService
import com.devspacecinenow.list.data.remote.MovieListRemoteDataSource

object CineNowServiceLocator {
    fun getRepo(applicationContext: Application): MovieListRepo {
        val db =
            Room.databaseBuilder(
                applicationContext,
                CineNowDatabase::class.java, "database-cine-now",
            ).build()

        val listService =
            RetrofitClient.retrofitInstance.create(ListService::class.java)


        val localDataSource: MovieListLocalDataSource =
            MovieListLocalDataSource(db.getMovieDao())


        val remoteDataSource: MovieListRemoteDataSource =
            MovieListRemoteDataSource(listService)


        return MovieListRepo(
            local = localDataSource,
            remote = remoteDataSource,
        )


    }
}