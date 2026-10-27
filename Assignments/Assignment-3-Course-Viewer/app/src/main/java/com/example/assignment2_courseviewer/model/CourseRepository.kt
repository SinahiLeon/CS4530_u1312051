package com.example.assignment2_courseviewer.model

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CourseRepository private constructor(
    private val scope: CoroutineScope,
    private val dao: CourseDao
) {
    val courses: Flow<List<Course>> = dao.getCourses()

    fun addCourse(course: Course) {
        scope.launch {
            dao.addCourse(course)
        }
    }

    fun deleteCourse(course: Course) {
        scope.launch {
            dao.deleteCourse(course)
        }
    }

    companion object {
        @Volatile
        private var instance: CourseRepository? = null

        fun getInstance(context: Context): CourseRepository =
            instance ?: synchronized(this) {
                instance ?: run {
                    val db = Room.databaseBuilder(
                        context.applicationContext,
                        CourseDatabase::class.java,
                        "courses.db"
                    ).build()

                    CourseRepository(
                        CoroutineScope(SupervisorJob() + Dispatchers.IO),
                        db.courseDao()
                    ).also { instance = it }
                }
            }
    }
}