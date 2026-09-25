package com.example.assignment2_courseviewer.ui.theme

import androidx.compose.runtime.Composable
import com.example.assignment2_courseviewer.model.Course
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Button

@Composable
fun CourseDetailsScreen(course: Course, onBack: () -> Unit, onDelete: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp)
    ) {
        Text(
            text = "Course Details",
            style = MaterialTheme.typography.headlineMedium
        )

        Text("Department: ${course.department}")
        Text("Course Number: ${course.courseNumber}")
        Text("Location: ${course.location}")

        Button(onClick = onBack)
        {
            Text("Back")
        }

        Button(onClick = onDelete)
        {
            Text("Delete")
        }

    }

}