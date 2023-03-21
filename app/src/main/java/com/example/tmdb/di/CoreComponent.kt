@file:Suppress("DEPRECATION")

package com.example.tmdb.di

import android.app.Application
import android.content.Context
import com.example.tmdb.di.modules.ContextModule
import com.example.tmdb.di.modules.NetworkModule
import com.example.tmdb.networking.api.MoviesAPI
import com.example.tmdb.networking.repo.MoviesRepository
import dagger.Component
import javax.inject.Singleton

/**
 * Core component that all module's components depend on.
 *
 * @see Component
 */
@Singleton
@Component(
    modules = [
        ContextModule::class,
        NetworkModule::class
    ]
)
interface CoreComponent {



    fun context(): Context

    //APIS
    fun movieService(): MoviesAPI





    //Repos
    fun movieRepository(): MoviesRepository



}

fun provideCoreComponent(application: Application): CoreComponent {
    return DaggerCoreComponent
        .builder()
        .contextModule(ContextModule(application = application))
        .build()
}
