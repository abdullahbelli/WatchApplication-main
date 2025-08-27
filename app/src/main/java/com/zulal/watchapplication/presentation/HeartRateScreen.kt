package com.zulal.watchapplication.presentation

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.*
import com.zulal.watchapplication.ui.theme.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect

// -----------------------------
// Heart Rate Sensor Manager
// -----------------------------
class HeartRateMonitor(context: Context) : SensorEventListener {
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val heartRateSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE)

    private val _heartRate = MutableStateFlow(0f)
    val heartRate = _heartRate.asStateFlow()

    fun start() {
        heartRateSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    fun stop() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            if (it.sensor.type == Sensor.TYPE_HEART_RATE) {
                _heartRate.value = it.values[0]
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}

// -----------------------------
// Heart Rate Screen Composable
// -----------------------------
@Composable
fun HeartRateScreen() {
    val context = LocalContext.current
    val heartRateMonitor = remember { HeartRateMonitor(context) }
    var currentHeartRate by remember { mutableStateOf(0f) }
    var isMonitoring by remember { mutableStateOf(false) }

    // Sensor başlat/durdur
    DisposableEffect(Unit) {
        if (isMonitoring) heartRateMonitor.start()
        onDispose { heartRateMonitor.stop() }
    }

    // StateFlow'u coroutine içinde dinle
    LaunchedEffect(heartRateMonitor, isMonitoring) {
        if (isMonitoring) {
            heartRateMonitor.heartRate.collect { bpm ->
                currentHeartRate = bpm
            }
        } else {
            currentHeartRate = 0f
        }
    }

    // UI
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp)
        ) {
            // Üstte ikon + başlık
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Heart",
                    tint = if (isMonitoring) Color.Red else OrangeAccent,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = "HEART RATE",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }

            // Ortadaki BPM değeri ve alt kutucuklar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatCardCompact(value = "68", label = "avg", icon = Icons.Default.BarChart)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = currentHeartRate.toInt().toString(),
                        color = if (isMonitoring) Color.Red else OrangeAccent,
                        fontWeight = FontWeight.Bold,
                        fontSize = 36.sp
                    )
                    Text(
                        text = "BPM",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
                StatCardCompact(value = "142", label = "max", icon = Icons.Default.ShowChart)
            }

            // Resting Zone Chip
            Chip(
                onClick = { /* TODO: Resting Zone functionality */ },
                label = { Text("Resting Zone", color = Color.White, fontSize = 12.sp) },
                colors = ChipDefaults.chipColors(
                    backgroundColor = Color(0xFF0A2B5E),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .width(120.dp)
                    .height(32.dp)
            )

            // Start Monitor Chip
            Chip(
                onClick = { isMonitoring = !isMonitoring },
                label = {
                    Text(
                        if (isMonitoring) "Monitoring" else "Start Monitor",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Bolt,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(14.dp)
                    )
                },
                colors = ChipDefaults.chipColors(
                    backgroundColor = if (isMonitoring) Color.Red else OrangeAccent,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .width(140.dp)
                    .height(38.dp)
            )
        }
    }
}

// -----------------------------
// Stat Card Compact Composable
// -----------------------------
@Composable
fun StatCardCompact(value: String, label: String, icon: ImageVector) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF1A1A1A))
            .padding(8.dp)
            .width(30.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFFAA66FF),
            modifier = Modifier.size(14.dp)
        )
        Text(value, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Text(label, color = Color.Gray, fontSize = 10.sp)
    }
}
