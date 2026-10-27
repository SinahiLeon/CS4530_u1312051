package com.example.assignment2_courseviewer.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.assignment2_courseviewer.model.Course
import com.example.assignment2_courseviewer.model.CourseRepository
import kotlinx.coroutines.launch

class CourseViewModel(
    private val repository: CourseRepository
) : ViewModel() {

    var listOfCourses by mutableStateOf<List<Course>>(emptyList())
        private set

    init {
        viewModelScope.launch {
            repository.courses.collect { courses ->
                listOfCourses = courses
            }
        }
    }

    fun addCourse(course: Course) {
        repository.addCourse(course)
    }

    fun deleteCourse(course: Course) {
        repository.deleteCourse(course)
    }
}

class CourseViewModelFactory(context: Context) : ViewModelProvider.Factory {
    private val repository = CourseRepository.getInstance(context.applicationContext)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CourseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CourseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}