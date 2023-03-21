package com.example.tmdb.ui.fragments.search.di


import com.example.tmdb.di.CoreComponent
import com.example.tmdb.di.scopes.FeatureScope
import com.example.tmdb.ui.fragments.search.SearchFragment
import dagger.Component


@FeatureScope
@Component(
    modules = [SearchModule::class],
    dependencies = [CoreComponent::class]
)

interface SearchComponent {
    fun inject(searchFragment: SearchFragment)
}
