package com.devspacecinenow.list

import app.cash.turbine.test
import com.devspacecinenow.common.data.local.MovieCategory
import com.devspacecinenow.common.data.model.Movie
import com.devspacecinenow.list.data.MovieListRepo
import com.devspacecinenow.list.presentation.MovieListViewModel
import com.devspacecinenow.list.presentation.ui.MovieListData
import com.devspacecinenow.list.presentation.ui.MovieListUiState
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MovieListViewModelTest {

    private val repository: MovieListRepo = mock()
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher(TestCoroutineScheduler())

    private val underTest by lazy {
        MovieListViewModel(repository, testDispatcher)
    }


    @Test
    fun `Given fresh viewModel When collecting to topRated Then assert expected value`() {
        runTest {
            // Given
            val movies = listOf(
                Movie(
                    id = 1,
                    title = "title1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.TopRated.name
                )
            )
            whenever(repository.getTopRated()).thenReturn(Result.success(movies))

            //When
            underTest.uiTopRated.test {
                // Then assert expected value
                val expected = MovieListUiState(
                    list = listOf(
                        MovieListData(
                            id = 1,
                            title = "title1",
                            overview = "overview1",
                            image = "image1"
                        )
                    )
                )
                assertEquals(expected, awaitItem())
            }
        }
    }


    @Test
    fun `Given fresh viewModel When collecting to NowPlaying Then assert expected value`() {
        runTest {
            // Given
            val movies = listOf(
                Movie(
                    id = 1,
                    title = "title1",
                    overview = "overview1",
                    image = "image1",
                    category = MovieCategory.NowPlaying.name
                )
            )
            whenever(repository.getNowPlaying()).thenReturn(Result.success(movies))

            //When
            underTest.uiNowplaying.test {
                // Then assert expected value
                val expected = MovieListUiState(
                    list = listOf(
                        MovieListData(
                            id = 1,
                            title = "title1",
                            overview = "overview1",
                            image = "image1"
                        )
                    )
                )
                assertEquals(expected, awaitItem())
            }
        }
    }
//
//    @Test
//    fun `Given fresh viewModel When collecting to topRated Then assert loading state`() {
//        runTest {
//            // Given
//            val movies = listOf(
//                Movie(
//                    id = 1,
//                    title = "title1",
//                    overview = "overview1",
//                    image = "image1",
//                    category = MovieCategory.TopRated.name
//                )
//            )
//            whenever(repository.getTopRated()).thenReturn(Result.success(movies))
//
//            //When
//            val result = underTest.uiTopRated.value
//
//            // Then assert expected value
//            val expected = MovieListUiState(isLoading = true)
//            assertEquals(expected, result)
//        }
//    }
}