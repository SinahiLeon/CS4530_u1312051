package com.example.assignment2_courseviewer.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import com.example.assignment2_courseviewer.model.Course
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Button


@Composable
fun AddCourseScreen(onSave: (Course) -> Unit, onCancel: () -> Unit) {
    // 3 input fields for each course
    var department by remember { mutableStateOf("") }
    var courseNumber by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp)
    ) {
        Text("Add Course")

        // Input for course department
        OutlinedTextField(
            value = department,
            onValueChange = { newDepartment -> department = newDepartment },
            label = { Text("Department") } // Floating label animation
        )

        // Input for course number
        OutlinedTextField(
            value = courseNumber,
            onValueChange = { newCourseNumber -> courseNumber = newCourseNumber },
            label = { Text("Course Number") }
        )

        // Input for location
        OutlinedTextField(
            value = location,
            onValueChange = { newLocation -> location = newLocation },
            label = { Text("Location") }
        )

        Button(onClick = {
            val newCourse = Course(
                department = department,
                courseNumber = courseNumber,
                location = location
            )
            onSave (newCourse)
        })
        { Text("Save Course") }
        Button(onClick = onCancel)
        {
            Text("Cancel")
        }
    }

}