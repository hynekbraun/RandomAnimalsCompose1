package com.hynekbraun.randomanimalscompose1.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query


@Dao
interface AnimalDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertAnimals(animalList: List<AnimalEntity>)

    @Query("DELETE FROM animalentity")
    suspend fun deleteAnimals()
}