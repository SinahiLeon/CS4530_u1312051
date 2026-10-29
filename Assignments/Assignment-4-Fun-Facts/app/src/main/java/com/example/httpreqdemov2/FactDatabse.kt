package com.example.httpreqdemov2

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SavedFact::class],
    version = 1,
    exportSchema = false
)

abstract class FactDatbase : RoomDatabase(){
    abstract fun factDao(): FactDao
}
