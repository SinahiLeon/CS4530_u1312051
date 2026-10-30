package com.example.httpreqdemov2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FactDao {
    @Insert
    suspend fun insertFact(fact: SavedFact)

    @Query("SELECT * FROM facts ORDER BY id DESC")
    fun getAllFunFacts(): Flow<List<SavedFact>>

    @Query("SELECT * FROM facts WHERE text LIKE :keyword ORDER BY id DESC")
    fun searchFacts(keyword: String): Flow<List<SavedFact>>
}