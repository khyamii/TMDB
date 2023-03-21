package com.example.tmdb.ui.fragments.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.extensions.ObservableString
import com.example.tmdb.networking.NetworkState
import com.example.tmdb.networking.entity.Movie
import com.example.tmdb.networking.repo.MoviesRepository
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


class SearchViewModel @Inject constructor(val moviesRepository: MoviesRepository) : ViewModel() {



    val networkState = MutableLiveData<NetworkState>()
    val movies = MutableLiveData<List<Movie>>()
    val searchText = ObservableString("")

    fun searchProducts(searchText: CharSequence){
        networkState.postValue(NetworkState.Loading())
        viewModelScope.launch(
            CoroutineExceptionHandler { _, t ->
                networkState.postValue(NetworkState.Error(errorMessage = t.message.toString()))
            }) {
            val response = moviesRepository.searchProducts(searchText =searchText.toString())

            if (response is NetworkResponse.Success  ) {
                movies.postValue(response.body.moviesList)
                networkState.postValue(NetworkState.Success(success = true))
            } else {

                networkState.postValue(NetworkState.Error(errorMessage = (response as NetworkResponse.ServerError).body!!.message))
            }

        }



    }




}
