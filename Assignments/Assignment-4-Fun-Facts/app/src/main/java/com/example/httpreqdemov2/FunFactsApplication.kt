package com.example.httpreqdemov2

import android.app.Application
import androidx.room.Room
import kotlin.jvm.java
import kotlin.getValue

class FunFactsApplication : Application() {
    val database by lazy{
        Room.databaseBuilder(
            applicationContext,
            FactDatabase::class.java,
            "facts_database"
        ).build()
    }

    val client by lazy {
        ApiClient.client
    }

    val repository by lazy{
        FactRepository.getInstance(database.factDao(), client)
    }
}