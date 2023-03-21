package com.example.tmdb.di

import android.content.Context
import com.example.tmdb.MyApp
import dagger.Module
import dagger.Provides

/**
 * Class that contributes to the object graph [AppComponent].
 *
 * @see Module
 */
@Module
class AppModule {

    @Provides
    fun provideContext(application: MyApp): Context = application.applicationContext
}
