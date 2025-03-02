package com.devspacecinenow.list

import com.devspacecinenow.common.data.model.Movie
import com.devspacecinenow.list.data.local.LocalDataSource

class FakeMovieListLocalDataSource: LocalDataSource {

    var nowPlaying = emptyList<Movie>()
    var topRate = emptyList<Movie>()
    var popular = emptyList<Movie>()
    var upcoming = emptyList<Movie>()
    var updateItems = emptyList<Movie>()

    override suspend fun getPlayingNow(): List<Movie> {
        return nowPlaying
    }

    override suspend fun getTopRated(): List<Movie> {
        return topRate
    }

    override suspend fun getPopular(): List<Movie> {
        return popular
    }

    override suspend fun getUpcoming(): List<Movie> {
        return upcoming
    }

    override suspend fun updateLocalItems(movies: List<Movie>) {
        updateItems = movies
    }
}