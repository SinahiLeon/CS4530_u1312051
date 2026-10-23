package com.example.assignment2_courseviewer.ui.theme

import androidx.compose.runtime.Composable
import com.example.assignment2_courseviewer.model.Course
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.items   // Allow lazyColumn to display each Course in list

import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Button


@Composable
fun CourseListScreen(
    courses: List<Course>,  // list of courses screen will display
    onCourseClick: (Course) -> Unit,
    onAddCourseClick: () -> Unit
)    // function called when user clicks course
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp)
    ) {
        Text(
            text = "My Courses",
            style = MaterialTheme.typography.headlineMedium
        )

         // Render the list of course names in scrollable way
        // 1f means the LazyColumn gets the space between title and Add Couse button
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(courses) { course ->
                Text(
                    text = "${course.department} ${course.courseNumber}",
                    // When course name is clicked, call onCourseClick
                    modifier = Modifier
                        .clickable { onCourseClick(course) }
                        .padding(vertical = 20.dp)
                )
            }

        }

        Button (onClick = onAddCourseClick) {
            Text ("Add Course")
        }

    }
}