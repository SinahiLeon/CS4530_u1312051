package com.example.assignment2_courseviewer.model

import android.content.Context
import androidx.room.Room

class CourseRepository private constructor(context: Context) {
    private val db = Room.databaseBuilder(
        context.applicationContext,
        CourseDatabase::class.java,
        "courses.db"
    ).build()

    val courses = db.courseDao().getCourses()

    suspend fun addCourse(course: Course) {
        db.courseDao().addCourse(course)
    }

    suspend fun deleteCourse(course: Course) {
        db.courseDao().deleteCourse(course)
    }

    companion object {
        @Volatile
        private var instance: CourseRepository? = null

        fun getInstance(context: Context): CourseRepository =
            instance ?: synchronized(this) {
                instance ?: CourseRepository(context).also { instance = it }
            }
    }
}