package com.devspacecinenow.detail.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.devspacecinenow.common.data.remote.RetrofitClient
import com.devspacecinenow.common.data.remote.model.MovieDto
import com.devspacecinenow.detail.data.DetailService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val detailService: DetailService
): ViewModel() {
    private val _uiDetailMovie = MutableStateFlow<MovieDto?>(null)
    val uiDetailMovie : StateFlow<MovieDto?> = _uiDetailMovie

    fun fetchMovieDetail(movieId : String){

        viewModelScope.launch(Dispatchers.IO) {
            val response = detailService.getMovieById(movieId)
            if (response.isSuccessful) {
                _uiDetailMovie.value = response.body()
            } else {
                Log.d("MovieDetailViewModel", "Request Error: ${response.errorBody()}")
            }
        }
    }

}