package com.example.tmdb.ui.fragments.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.networking.NetworkState
import com.example.tmdb.networking.repo.MoviesRepository
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.tmdb.networking.entity.Movie


class FavoritesViewModel @Inject constructor(
    val moviesRepository: MoviesRepository,
) : ViewModel() {


    val networkState = MutableLiveData<NetworkState>()
    val movies = MutableLiveData<List<Movie>>()


    init {
        fetchFavorites()
    }

    private fun fetchFavorites() {
        networkState.postValue(NetworkState.Loading())
        viewModelScope.launch(
            CoroutineExceptionHandler { _, t ->
                networkState.postValue(NetworkState.Error(errorMessage = t.message.toString()))
            }) {
            val response = moviesRepository.fetchFavorites()

            if (response is NetworkResponse.Success) {
                movies.postValue(response.body.moviesList)
                networkState.postValue(NetworkState.Success(success = true))
            } else {
                networkState.postValue(NetworkState.Error(errorMessage = (response as NetworkResponse.ServerError).body!!.message))
            }

        }
    }



}
