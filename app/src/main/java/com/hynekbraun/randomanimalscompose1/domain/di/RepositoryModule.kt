package com.hynekbraun.randomanimalscompose1.domain.di

import com.hynekbraun.randomanimalscompose1.data.repository.AnimalFactRepositoryImp
import com.hynekbraun.randomanimalscompose1.domain.repository.AnimalFactRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAnimalFactRepository(
        animalFactRepositoryImp: AnimalFactRepositoryImp
    ): AnimalFactRepository

}