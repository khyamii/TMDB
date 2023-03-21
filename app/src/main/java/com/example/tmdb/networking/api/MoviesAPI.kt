package com.example.tmdb.networking.api

import com.example.tmdb.networking.entity.BaseErrorResponse
import com.example.tmdb.networking.entity.MovieResponse
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.*


interface MoviesAPI {


    @GET("{path}?api_key=be97c27483698ad7331a51a66be4e1ee&language=en-US&page=1")
    suspend fun fetchMovies(
        @Path("path") path: String,
    ): NetworkResponse<MovieResponse, BaseErrorResponse>

    @GET("search/movie?api_key=be97c27483698ad7331a51a66be4e1ee&page=1")
    suspend fun searchProducts(
        @Query("query") searchText: String,
    ): NetworkResponse<MovieResponse, BaseErrorResponse>

    @GET("account/{account_id}/favorite/movies?api_key=be97c27483698ad7331a51a66be4e1ee")
    suspend fun fetchFavoritesList(
        @Header("Authorization") token: String?,

    ): NetworkResponse<MovieResponse, BaseErrorResponse>


}
