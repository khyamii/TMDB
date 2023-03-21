package com.example.tmdb.ui.fragments.main.movies.di

import com.example.tmdb.di.scopes.FeatureScope
import com.example.tmdb.extensions.viewModel
import com.example.tmdb.networking.repo.MoviesRepository
import com.example.tmdb.ui.fragments.main.movies.MoviesViewModel

import com.example.tmdb.ui.fragments.main.movies.MoviesFragment
import dagger.Module
import dagger.Provides

@Module
class MoviesModule (val fragment: MoviesFragment){

    @FeatureScope
    @Provides
    fun providesMoviesViewModel(
        moviesRepository:  MoviesRepository) = fragment.viewModel { MoviesViewModel(moviesRepository = moviesRepository) }
}