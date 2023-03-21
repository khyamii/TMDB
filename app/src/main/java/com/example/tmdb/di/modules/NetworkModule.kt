package com.example.tmdb.di.modules

import android.content.Context
import com.example.tmdb.BuildConfig
import com.example.tmdb.networking.api.MoviesAPI
import com.example.tmdb.networking.repo.MoviesRepository
import com.example.tmdb.utilities.GlobalVariables
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory

import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return httpLoggingInterceptor
    }







    @Singleton
    @Provides
    fun provideHttpClient(
        interceptor: HttpLoggingInterceptor,
        application: Context
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(interceptor)
        clientBuilder.followRedirects(true)
        clientBuilder.followSslRedirects(true)
        clientBuilder.retryOnConnectionFailure(true)
        clientBuilder.connectTimeout(25, TimeUnit.SECONDS)
        clientBuilder.readTimeout(25, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(25, TimeUnit.SECONDS)
        clientBuilder.cache(Cache(application.cacheDir, (10 * 1024 * 1024).toLong()))
        return clientBuilder.build()
    }


    @Singleton
    @Provides
    fun provideRetrofitBuilder(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(GlobalVariables.BaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .client(client)
        .build()



    // APIS

    @Singleton
    @Provides
    fun provideMoviesService(retrofit: Retrofit): MoviesAPI = retrofit.create(MoviesAPI::class.java)



    //REPOS

    @Singleton
    @Provides
    fun provideMoviesRepository(homeService: MoviesAPI) = MoviesRepository(homeService)



}