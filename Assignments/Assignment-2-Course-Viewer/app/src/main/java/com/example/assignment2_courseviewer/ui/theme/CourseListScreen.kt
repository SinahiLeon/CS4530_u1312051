package com.example.assignment2_courseviewer.ui.theme

import androidx.compose.runtime.Composable
import com.example.assignment2_courseviewer.model.Course
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.items   // to be able to use course.attributes

import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable


@Composable
fun CourseListScreen(
        courses: List<Course>,  // list of courses screen will display
        onCourseClick: (Course) -> Unit)    // function called when user clicks course
{
    Column {
        Text("My Courses")

        LazyColumn{ // Render the list of course names in scrollable way
            items(courses) {course ->
                Text( text = "${course.department} ${course.courseNumber}",
                    // When course name is clicked, call onCourseClick
                    modifier = Modifier.clickable { onCourseClick (course)}
                )
            }

        }

    }
}