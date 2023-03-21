package com.example.tmdb.networking.repo
import com.example.tmdb.networking.api.MoviesAPI
import com.example.tmdb.networking.entity.BaseErrorResponse
import com.example.tmdb.networking.entity.MovieResponse
import com.example.tmdb.utilities.GlobalVariables
import com.haroldadmin.cnradapter.NetworkResponse


class MoviesRepository(


    private val moviesService: MoviesAPI,
) {

    suspend fun fetchMoviesList(path:String): NetworkResponse<MovieResponse, BaseErrorResponse> {

        return moviesService.fetchMovies(path)
    }

    suspend fun searchProducts(searchText: String): NetworkResponse<MovieResponse, BaseErrorResponse> {
        return moviesService.searchProducts(searchText = searchText)
    }

    suspend fun fetchFavorites(): NetworkResponse<MovieResponse, BaseErrorResponse> {
        return moviesService.fetchFavoritesList("bearer "+GlobalVariables.ACCESS_TOKEN)
    }




}

