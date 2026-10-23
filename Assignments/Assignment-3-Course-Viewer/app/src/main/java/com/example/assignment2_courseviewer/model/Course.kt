package com.example.assignment2_courseviewer.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class Course(
    val department: String,
    val courseNumber: String,
    val location: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)