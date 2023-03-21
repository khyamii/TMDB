package com.example.tmdb.ui.fragments.favorite.di


import com.example.tmdb.di.scopes.FeatureScope
import com.example.tmdb.networking.repo.MoviesRepository
import com.example.tmdb.ui.fragments.favorite.FavoriteFragment
import com.example.tmdb.ui.fragments.favorite.FavoritesViewModel
import dagger.Module
import dagger.Provides
import com.example.tmdb.extensions.viewModel


@Module
class FavoritesModule(val fragment: FavoriteFragment) {


    @FeatureScope
    @Provides
    fun providesFavoritesViewModel(
        moviesRepository: MoviesRepository,

    ) = fragment.viewModel {
        FavoritesViewModel(moviesRepository = moviesRepository)
    }


}