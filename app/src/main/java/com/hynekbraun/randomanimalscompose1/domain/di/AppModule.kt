package com.hynekbraun.randomanimalscompose1.domain.di

import android.app.Application
import androidx.room.Room
import com.hynekbraun.randomanimalscompose1.data.local.AnimalFactDatabase
import com.hynekbraun.randomanimalscompose1.data.remote.AnimalFactApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAnimalFactApi(): AnimalFactApi {
        return Retrofit.Builder()
            .baseUrl(AnimalFactApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideAnimalFactDatabase(app: Application): AnimalFactDatabase {
        return Room.databaseBuilder(
            app,
            AnimalFactDatabase::class.java,
            "animalfact.db"
        ).build()
    }
}
