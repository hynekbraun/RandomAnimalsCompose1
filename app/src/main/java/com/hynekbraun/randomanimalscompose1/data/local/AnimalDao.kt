package com.hynekbraun.randomanimalscompose1.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query


@Dao
interface AnimalDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertAnimals(animalFactList: List<AnimalFactEntity>)

    @Query("DELETE FROM animalfactentity")
    suspend fun deleteAnimals()
}