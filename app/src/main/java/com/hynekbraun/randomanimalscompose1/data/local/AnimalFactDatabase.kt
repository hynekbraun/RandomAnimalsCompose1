package com.hynekbraun.randomanimalscompose1.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [AnimalFactEntity::class],
    version = 1
)
abstract class AnimalFactDatabase : RoomDatabase() {

    abstract val factDao: AnimalFactDao
}