package com.hynekbraun.randomanimalscompose1.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface AnimalFactDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertAnimals(animalFactList: List<AnimalFactEntity>)

    @Query("DELETE FROM animalfactentity")
    suspend fun deleteAnimals()

    @Query("SELECT * FROM animalfactentity")
    suspend fun getAnimals(): List<AnimalFactEntity>

    @Query("SELECT * FROM animalfactentity WHERE id = :id")
    suspend fun getAnimalById(id: Int): AnimalFactEntity
}