package com.example.assignment2_courseviewer.ui.theme

import androidx.compose.runtime.Composable
import com.example.assignment2_courseviewer.model.Course
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Column


@Composable
fun CourseDetailsScreen(course: Course) {
    Column {
        Text("Course Detials")

        Text("Department: ${course.department}")
        Text("Course Number: ${course.courseNumber}")
        Text("Location: ${course.location}")
    }

}