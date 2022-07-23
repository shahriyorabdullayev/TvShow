package com.shahriyor.android_imperative.di

import android.app.Application
import com.shahriyor.android_imperative.data.local.AppDatabase
import com.shahriyor.android_imperative.data.local.dao.TVShowDao
import com.shahriyor.android_imperative.data.remote.Server
import com.shahriyor.android_imperative.data.remote.Server.IS_TESTER
import com.shahriyor.android_imperative.data.remote.service.TvShowService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    /**
     * Retrofit Related
     */

    @Provides
    fun server(): String {
        if (IS_TESTER) return Server.getServerDevelopment()
        return Server.getServerProduction()
    }

    @Provides
    @Singleton
    fun retrofitClient(): Retrofit {
        return Retrofit.Builder().baseUrl(server())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun tvShowService(): TvShowService {
        return retrofitClient().create(TvShowService::class.java)
    }

    /**
     * Room Related
     */

    @Provides
    @Singleton
    fun appDatabase(context: Application): AppDatabase {
        return AppDatabase.getAppDBInstance(context)
    }


    @Provides
    @Singleton
    fun tvShowDao(appDatabase: AppDatabase): TVShowDao {
        return appDatabase.getTVShowDao()
    }

}