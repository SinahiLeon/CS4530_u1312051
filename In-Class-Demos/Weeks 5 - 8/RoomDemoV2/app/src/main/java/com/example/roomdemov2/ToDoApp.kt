package com.example.roomdemov2

import android.app.Application
import androidx.room.Room
import com.example.roomdemov2.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ToDoApp: Application() {

    val scope = CoroutineScope(SupervisorJob())

    val db by lazy {
        Room.databaseBuilder(applicationContext,
            AppDatabase::class.java,
            "ToDoAppDatabase").build()
    }

    val repository by lazy { Repository(scope, db.taskDao()) }
}