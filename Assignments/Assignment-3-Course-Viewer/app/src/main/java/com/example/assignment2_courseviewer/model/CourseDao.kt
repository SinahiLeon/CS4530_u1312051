package com.example.assignment2_courseviewer.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
    @Query("SELECT * FROM courses ORDER BY id")
    fun getCourses(): Flow<List<Course>>

    @Insert
    suspend fun addCourse(course: Course)

    @Delete
    suspend fun deleteCourse(course: Course)
}