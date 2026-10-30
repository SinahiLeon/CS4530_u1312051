package com.example.httpreqdemov2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "facts")
data class SavedFact (
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    val text: String
)