package com.devspacecinenow.list.data.local

import com.devspacecinenow.common.data.local.MovieCategory
import com.devspacecinenow.common.data.local.MovieDao
import com.devspacecinenow.common.data.local.MovieEntity
import com.devspacecinenow.common.data.model.Movie

class MovieListLocalDataSouce(
    private val dao: MovieDao,
) {

    suspend fun getPlayingNow(): List<Movie>{
        return getMoviesByCategory(MovieCategory.NowPlaying)
    }

    suspend fun getUpcoming(): List<Movie>{
        return getMoviesByCategory(MovieCategory.Upcoming)
    }

    suspend fun getTopRated(): List<Movie>{
        return getMoviesByCategory(MovieCategory.TopRated)
    }

    suspend fun getPopular(): List<Movie>{
        return getMoviesByCategory(MovieCategory.Popular)
    }

    suspend fun updateLocalItems(movies: List<Movie>){
        val entities = movies.map {
            MovieEntity(
                id = it.id,
                title = it.title,
                overview = it.overview,
                image = it.image,
                category = it.category
            )
        }
            dao.insertAll(entities)
    }

    private suspend fun getMoviesByCategory(movieCategory: MovieCategory): List<Movie>{
        val entities = dao.getMoviesByCategory(movieCategory.name)

        return entities.map {
            Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                image = it.image,
                category = it.category,
            )
        }
    }
}