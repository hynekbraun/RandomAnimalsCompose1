package com.hynekbraun.randomanimalscompose1.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [AnimalEntity::class],
    version = 1
)
abstract class AnimalDatabase : RoomDatabase() {

    abstract val dao: AnimalDao
}