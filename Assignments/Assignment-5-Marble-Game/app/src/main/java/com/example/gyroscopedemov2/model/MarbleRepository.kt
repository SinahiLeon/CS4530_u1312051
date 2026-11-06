package com.example.gyroscopedemov2.model

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

data class GravityReading(val x: Float, val y: Float, val z: Float)

class MarbleRepository(context: Context) {
    val sensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    // Gravity sensor with accelerometer fallback
    val gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)

    val sensor = gravitySensor ?: sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    var listener: SensorEventListener? = null

    // Register sensor with game update speed
    fun startListening(onReading: (GravityReading) -> Unit)
    {
        if (sensor == null || listener != null)
            return

        val newListener = object: SensorEventListener{
            override fun onSensorChanged(event: SensorEvent) {
                onReading(
                    GravityReading(
                        event.values[0],
                        event.values[1],
                        event.values[2]
                    )
                )
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit
        }
        registerSensor(newListener)
    }


    private fun registerSensor(newListener: SensorEventListener) {
        if (sensorManager.registerListener(newListener, sensor, SensorManager.SENSOR_DELAY_GAME ))
        {
            listener = newListener
        }
    }

    // Unregister sensor
    fun stopListening() {
        listener?.let{
            sensorManager.unregisterListener(it)
        }
        listener = null
    }

}