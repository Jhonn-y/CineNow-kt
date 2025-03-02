package com.devspacecinenow.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.devspacecinenow.CineNowApplication
import com.devspacecinenow.common.data.remote.RetrofitClient
import com.devspacecinenow.list.data.remote.ListService
import com.devspacecinenow.list.data.MovieListRepo
import com.devspacecinenow.list.presentation.ui.MovieListData
import com.devspacecinenow.list.presentation.ui.MovieListUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class MovieListViewModel(
    private val movieListRepo: MovieListRepo,
    private val dispatcher : CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {
    private val _uiNowplaying = MutableStateFlow(MovieListUiState())
    val uiNowplaying: StateFlow<MovieListUiState> = _uiNowplaying

    private val _uiTopRated = MutableStateFlow(MovieListUiState())
    val uiTopRated: StateFlow<MovieListUiState> = _uiTopRated

    private val _uiPopular = MutableStateFlow(MovieListUiState())
    val uiPopular: StateFlow<MovieListUiState> = _uiPopular

    private val _uiUpcoming = MutableStateFlow(MovieListUiState())
    val uiUpcoming: StateFlow<MovieListUiState> = _uiUpcoming

    init {
        fetchNowPlaying()
        fetchTopRated()
        fetchPopular()
        fetchUpcoming()

    }

    private fun fetchNowPlaying() {
        _uiNowplaying.value = MovieListUiState(isLoading = true)
        viewModelScope.launch(dispatcher) {

            val result = movieListRepo.getNowPlaying()
            if (result.isSuccess) {
                val movies = result.getOrNull()
                if (movies != null) {
                    val movieListData = movies.map { movieDto ->
                        MovieListData(
                            id = movieDto.id,
                            title = movieDto.title,
                            overview = movieDto.overview,
                            image = movieDto.image,
                        )
                    }
                    _uiNowplaying.value = MovieListUiState(list = movieListData)
                } else {
                    val ex = result.exceptionOrNull()
                    if (ex is UnknownHostException) {
                        _uiNowplaying.value = MovieListUiState(
                            isError = true,
                            errorMessage = "Not internet Connection"
                        )

                    } else {
                        _uiNowplaying.value = MovieListUiState(isError = true)

                    }
                }
            }

        }
    }

    private fun fetchTopRated() {
        _uiTopRated.value = MovieListUiState(isLoading = true)
        viewModelScope.launch(dispatcher) {
            val response = movieListRepo.getTopRated()
            if (response.isSuccess) {
                val movies = response.getOrNull()
                if (movies != null) {
                    val movieUiDataList = movies.map { movieDto ->
                        MovieListData(
                            id = movieDto.id,
                            title = movieDto.title,
                            overview = movieDto.overview,
                            image = movieDto.image
                        )
                    }
                    _uiTopRated.value = MovieListUiState(list = movieUiDataList)
                }
            } else {
                val ex = response.exceptionOrNull()
                if (ex is UnknownHostException) {
                    _uiTopRated.value = MovieListUiState(
                        isError = true,
                        errorMessage = "Not internet connection"
                    )
                } else {
                    _uiTopRated.value = MovieListUiState(isError = true)
                }
            }
        }
    }

    private fun fetchPopular() {
        _uiPopular.value = MovieListUiState(isLoading = true)
        viewModelScope.launch(dispatcher) {
                val response = movieListRepo.getPopular()
                if (response.isSuccess) {
                    val movies = response.getOrNull()
                    if (movies != null) {
                        val movieListData = movies.map { movieDto ->
                            MovieListData(
                                id = movieDto.id,
                                title = movieDto.title,
                                overview = movieDto.overview,
                                image = movieDto.image
                            )
                        }
                        _uiPopular.value = MovieListUiState(list = movieListData)
                    }
                } else {
                    val ex = response.exceptionOrNull()
                    if (ex is UnknownHostException) {
                        _uiPopular.value = MovieListUiState(
                            isError = true,
                            errorMessage = "Not internet Connection"
                        )

                    } else {
                        _uiPopular.value = MovieListUiState(isError = true)

                    }
                }
        }
    }

    private fun fetchUpcoming() {
        _uiUpcoming.value = MovieListUiState(isLoading = true)
        viewModelScope.launch(dispatcher) {
                val response = movieListRepo.getUpcoming()
                if (response.isSuccess) {
                    val movies = response.getOrNull()
                    if (movies != null) {
                        val movieListData = movies.map { movieDto ->
                            MovieListData(
                                id = movieDto.id,
                                title = movieDto.title,
                                overview = movieDto.overview,
                                image = movieDto.image
                            )
                        }
                        _uiUpcoming.value = MovieListUiState(list = movieListData)

                    }
                } else {
                    val ex = response.exceptionOrNull()
                    if (ex is UnknownHostException) {
                        _uiPopular.value = MovieListUiState(
                            isError = true,
                            errorMessage = "Not internet Connection"
                        )

                    } else {
                        _uiPopular.value = MovieListUiState(isError = true)

                    }
                }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                RetrofitClient.retrofitInstance.create(ListService::class.java)
                val application = checkNotNull(extras[APPLICATION_KEY])
                return MovieListViewModel(
                    movieListRepo = (application as CineNowApplication).repository
                ) as T
            }
        }
    }
}
