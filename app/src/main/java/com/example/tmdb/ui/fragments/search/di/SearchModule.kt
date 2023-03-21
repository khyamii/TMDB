package com.example.tmdb.ui.fragments.search.di


import com.example.tmdb.ui.fragments.search.SearchFragment
import com.example.tmdb.ui.fragments.search.SearchViewModel
import com.example.tmdb.di.scopes.FeatureScope
import com.example.tmdb.networking.repo.MoviesRepository
import com.example.tmdb.extensions.viewModel
import dagger.Module
import dagger.Provides


@Module
class SearchModule(val fragment: SearchFragment) {


    @FeatureScope
    @Provides
    fun providesSearchViewModel(
        moviesRepository: MoviesRepository) = fragment.viewModel {
        SearchViewModel(moviesRepository= moviesRepository)
    }


}