package com.devspacecinenow.list.data.remote

import android.accounts.NetworkErrorException
import com.devspacecinenow.common.data.local.MovieCategory
import com.devspacecinenow.common.data.model.Movie
import com.devspacecinenow.common.data.remote.model.MovieResponse

class MovieListRemoteDataSouce(
    private val listService: ListService,
) {

    suspend fun getNowPlaying(): Result<List<Movie>?> {
        return try {
            val response = listService.getNowPlayingMovies()
            if (response.isSuccessful) {
                val movies = response.body()?.results?.map {
                    Movie(
                        id = it.id,
                        title = it.title,
                        overview = it.overview,
                        image = it.postFullPath,
                        category = MovieCategory.NowPlaying.name
                    )
                }
                Result.success(movies)
            } else {
                Result.failure(NetworkErrorException(response.message()))
            }
        } catch (ex: Exception) {
            Result.failure(ex)
        }

    }

    suspend fun getTopRated(): Result<List<Movie>?> {
        return try {
            val response = listService.getTopRatedMovies()
            if (response.isSuccessful) {
                val movies = response.body()?.results?.map {
                    Movie(
                        id = it.id,
                        title = it.title,
                        overview = it.overview,
                        image = it.postFullPath,
                        category = MovieCategory.TopRated.name
                    )
                }
                Result.success(movies)

            } else {
                Result.failure(NetworkErrorException(response.message()))
            }
        } catch (ex: Exception) {
            Result.failure(ex)
        }

    }

    suspend fun getPopular(): Result<List<Movie>?> {
        return try {
            val response = listService.getPopularMovies()
            if (response.isSuccessful) {
                val movies = response.body()?.results?.map {
                    Movie(
                        id = it.id,
                        title = it.title,
                        overview = it.overview,
                        image = it.postFullPath,
                        category = MovieCategory.Popular.name
                    )
                }
                Result.success(movies)
            } else {
                Result.failure(NetworkErrorException(response.message()))
            }

        } catch (ex: Exception) {
            Result.failure(ex)
        }

    }

    suspend fun getUpcoming(): Result<List<Movie>?> {
        return try {
            val response = listService.getUpcomingMovies()
            if (response.isSuccessful) {
                val movies = response.body()?.results?.map {
                    Movie(
                        id = it.id,
                        title = it.title,
                        overview = it.overview,
                        image = it.postFullPath,
                        category = MovieCategory.Upcoming.name
                    )
                }
                Result.success(movies)
            } else {
                Result.failure(NetworkErrorException(response.message()))
            }
        } catch (ex: Exception) {
            Result.failure(ex)
        }

    }
}