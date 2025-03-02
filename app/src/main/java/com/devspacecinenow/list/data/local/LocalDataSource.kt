package com.devspacecinenow.list.data.local

import com.devspacecinenow.common.data.model.Movie

interface LocalDataSource {
    suspend fun getPlayingNow(): List<Movie>

    suspend fun getTopRated(): List<Movie>

    suspend fun getPopular(): List<Movie>

    suspend fun getUpcoming(): List<Movie>

    suspend fun updateLocalItems(movies: List<Movie>)
}