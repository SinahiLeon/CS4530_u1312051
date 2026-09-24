package com.example.assignment2_courseviewer.viewmodel

// Referencing model.Course
import com.example.assignment2_courseviewer.model.Course
import androidx.compose.runtime.mutableStateOf  // to turn our list into state that Compose can observe
import androidx.lifecycle.ViewModel     // to use MVVM architecture
// Access the properties (like value of course) listOfCourses
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class CourseViewModel : ViewModel() {
    var listOfCourses by mutableStateOf(
        listOf(
            Course("CS", "4530", "WEB L104"),
            Course("CS", "3550", "WEB 103"),
            Course("ETHC", "3390", "LIB 105")
        )
    )
        private set

    fun addCourse(course: Course){
        listOfCourses = listOfCourses + course;
    }

    fun deleteCourse(course: Course) {
        listOfCourses = listOfCourses - course;
    }

}