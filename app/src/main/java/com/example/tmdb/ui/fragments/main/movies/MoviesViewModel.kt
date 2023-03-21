package com.example.tmdb.ui.fragments.main.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.networking.entity.Movie
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import com.example.tmdb.networking.NetworkState
import com.example.tmdb.networking.repo.MoviesRepository
import com.haroldadmin.cnradapter.NetworkResponse
import javax.inject.Inject


class MoviesViewModel @Inject constructor(val moviesRepository: MoviesRepository) : ViewModel() {

    val networkState = MutableLiveData<NetworkState>()
    val movies = MutableLiveData<List<Movie>>()




     fun fetchMovies(path:String) {
        networkState.postValue(NetworkState.Loading())
        viewModelScope.launch(
            CoroutineExceptionHandler { _, t ->
                networkState.postValue(NetworkState.Error(errorMessage = t.message.toString()))
            }) {
            val response = moviesRepository.fetchMoviesList(path)

            if (response is NetworkResponse.Success) {
                movies.postValue(response.body.moviesList)
                networkState.postValue(NetworkState.Success(success = true))
            } else {
                networkState.postValue(NetworkState.Error(errorMessage = (response as NetworkResponse.ServerError).body!!.message))
            }

        }
    }


}
