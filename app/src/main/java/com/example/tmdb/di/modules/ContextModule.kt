package com.example.tmdb.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ContextModule(private val application: Application) {


    @Singleton
    @Provides
    fun provideContext(): Context = application
}
