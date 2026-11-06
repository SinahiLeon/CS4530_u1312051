package com.example.gyroscopedemov2.viewmodel
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class MarbleViewModel : ViewModel() {
    var x by mutableStateOf(0f)
        private set

    var y by mutableStateOf(0f)
        private set
    private var velocityX = 0f
    private var velocityY = 0f

    // Display marble ball that moves according to device's tilt
    fun updatePosition(gravityX: Float, gravityY: Float, dt: Float,
                       maxX: Float, maxY: Float) {
        val acceleration = 200f
        val friction = 0.98f

        // The marble's motion should slow down naturally (basic friction)
        velocityX += gravityX * acceleration * dt
        velocityY -= gravityY * acceleration * dt

        velocityX *= friction
        velocityY *= friction

        // Keep marble within screen boundaries
        x = (x + velocityX * dt).coerceIn(0f, maxX)
        y = (y + velocityY * dt).coerceIn(0f, maxY)

        if (x == 0f || x == maxX) velocityX = 0f
        if (y == 0f || y == maxY) velocityY = 0f
    }

}