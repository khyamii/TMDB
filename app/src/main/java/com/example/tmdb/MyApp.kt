package com.example.tmdb

import android.app.Application
import android.content.Context
import com.example.tmdb.di.modules.ContextModule
import com.example.tmdb.di.CoreComponent
import com.example.tmdb.di.DaggerCoreComponent


class MyApp : Application() {

    lateinit var coreComponent: CoreComponent


    companion object {

        @JvmStatic
        fun coreComponent(context: Context) = (context.applicationContext as? MyApp)?.coreComponent
    }


    override fun onCreate() {
        super.onCreate()
        initCoreDependencyInjection()

    }


    private fun initCoreDependencyInjection() {
        coreComponent = DaggerCoreComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }

}


