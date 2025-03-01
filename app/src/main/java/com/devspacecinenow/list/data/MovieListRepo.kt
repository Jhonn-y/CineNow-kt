package com.devspacecinenow.list.data

import com.devspacecinenow.common.data.model.Movie
import com.devspacecinenow.list.data.local.MovieListLocalDataSouce
import com.devspacecinenow.list.data.remote.MovieListRemoteDataSouce

class MovieListRepo(
    private val local: MovieListLocalDataSouce,
    private val remote: MovieListRemoteDataSouce,
) {

    suspend fun getNowPlaying(): Result<List<Movie>?> {
        return try {
            val result = remote.getNowPlaying()
            if (result.isSuccess) {
                val movieRemote = result.getOrNull() ?: emptyList()
                if (movieRemote.isNotEmpty()) {
                    local.updateLocalItems(movieRemote)

                    return Result.success(local.getPlayingNow())
                } else {
                    val localData = local.getPlayingNow()
                    if (localData.isEmpty()) {
                        return result
                    } else {
                        Result.success(localData)
                    }
                }
            }
            return Result.success(local.getPlayingNow())
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }


    suspend fun getTopRated(): Result<List<Movie>?> {
        return try {
            val result = remote.getTopRated()
            if (result.isSuccess) {
                val movieRemote = result.getOrNull() ?: emptyList()
                if (movieRemote.isNotEmpty()) {
                    local.updateLocalItems(movieRemote)

                    return Result.success(local.getTopRated())
                } else {
                    val localData = local.getTopRated()
                    if (localData.isEmpty()) {
                        return result
                    } else {
                        Result.success(localData)
                    }
                }
            }
            return Result.success(local.getTopRated())
        } catch (ex: Exception) {
            Result.failure(ex)
        }

    }

    suspend fun getPopular(): Result<List<Movie>?> {
        return try {
            val result = remote.getPopular()
            if (result.isSuccess) {
                val movieRemote = result.getOrNull() ?: emptyList()
                if (movieRemote.isNotEmpty()) {
                    local.updateLocalItems(movieRemote)

                    return Result.success(local.getPopular())
                } else {
                    val localData = local.getPopular()
                    if (localData.isEmpty()) {
                        return result
                    } else {
                        Result.success(localData)
                    }
                }
            }
            return Result.success(local.getPopular())
        } catch (ex: Exception) {
            Result.failure(ex)
        }

    }

    suspend fun getUpcoming(): Result<List<Movie>?> {
        return try {
            val result = remote.getUpcoming()
            if (result.isSuccess) {
                val movieRemote = result.getOrNull() ?: emptyList()
                if (movieRemote.isNotEmpty()) {
                    local.updateLocalItems(movieRemote)

                    return Result.success(local.getUpcoming())
                } else {
                    val localData = local.getUpcoming()
                    if (localData.isEmpty()) {
                        return result
                    } else {
                        Result.success(localData)
                    }
                }
            }
            return Result.success(local.getUpcoming())
        } catch (ex: Exception) {
            Result.failure(ex)
        }

    }
}