package com.example.tmdb.ui.fragments.main.movies.di

import com.example.tmdb.di.CoreComponent
import com.example.tmdb.di.scopes.FeatureScope
import com.example.tmdb.ui.fragments.main.movies.MoviesFragment
import dagger.Component


@FeatureScope
@Component(
    modules = [MoviesModule::class],
    dependencies = [CoreComponent::class]
)

interface MoviesComponent {
    fun inject(moviesFragment: MoviesFragment)
}
