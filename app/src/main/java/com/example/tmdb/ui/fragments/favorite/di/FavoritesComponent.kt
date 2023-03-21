package com.example.tmdb.ui.fragments.favorite.di


import com.example.tmdb.di.CoreComponent
import com.example.tmdb.di.scopes.FeatureScope
import com.example.tmdb.ui.fragments.favorite.FavoriteFragment
import dagger.Component


@FeatureScope
@Component(
    modules = [FavoritesModule::class],
    dependencies = [CoreComponent::class]
)

interface FavoritesComponent {
    fun inject(favoritesFragment: FavoriteFragment)
}
