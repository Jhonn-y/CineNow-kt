package com.devspacecinenow.list.data.remote

import android.accounts.NetworkErrorException
import com.devspacecinenow.common.data.local.MovieCategory
import com.devspacecinenow.common.data.model.Movie

interface RemoteDataSouce {
    suspend fun getNowPlaying(): Result<List<Movie>?>

    suspend fun getTopRated(): Result<List<Movie>?>

    suspend fun getPopular(): Result<List<Movie>?>

    suspend fun getUpcoming(): Result<List<Movie>?>
}