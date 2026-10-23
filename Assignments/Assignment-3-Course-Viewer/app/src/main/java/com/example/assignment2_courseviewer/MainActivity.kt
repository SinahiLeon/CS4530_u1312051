package com.example.assignment2_courseviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.assignment2_courseviewer.ui.theme.Assignment2CourseViewerTheme
import com.example.assignment2_courseviewer.viewmodel.CourseViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.remember
import com.example.assignment2_courseviewer.model.Course
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.assignment2_courseviewer.ui.theme.CourseDetailsScreen
import com.example.assignment2_courseviewer.ui.theme.CourseListScreen
import com.example.assignment2_courseviewer.ui.theme.AddCourseScreen


enum class Screen {
    LIST,
    DETAILS,
    ADD
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val courseViewModel: CourseViewModel = viewModel()

            // Remember which screen is being displayed
            var currentScreen by remember { mutableStateOf(Screen.LIST) }
            // Can contain a course object or null
            var selectedCourse by remember { mutableStateOf<Course?>(null) }

            Assignment2CourseViewerTheme {
                when (currentScreen) {
                    Screen.LIST -> {
                        CourseListScreen(
                            courses = courseViewModel.listOfCourses,
                            onCourseClick = { course ->
                                selectedCourse = course
                                currentScreen = Screen.DETAILS
                            },
                            onAddCourseClick = {
                                currentScreen = Screen.ADD
                            }
                        )
                    }

                    Screen.ADD -> {
                        AddCourseScreen (
                            onSave = { newCourse -> courseViewModel.addCourse(newCourse)
                            currentScreen = Screen.LIST
                            },
                            onCancel = {currentScreen = Screen.LIST}

                        )
                    }

                    Screen.DETAILS -> {
                        selectedCourse?.let { course ->
                            CourseDetailsScreen(
                                course = course,
                                onBack = { currentScreen = Screen.LIST },
                                onDelete = {
                                    courseViewModel.deleteCourse(course)
                                    // Deleted course no longer exists
                                    selectedCourse = null
                                    currentScreen = Screen.LIST
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

