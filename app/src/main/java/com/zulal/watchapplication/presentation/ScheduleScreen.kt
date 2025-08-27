package com.zulal.watchapplication.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import com.zulal.watchapplication.ui.theme.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Icon

@Composable
fun ScheduleScreen() {
    val totalTasks = 4
    var taskStatus by remember { mutableStateOf(List(totalTasks) { false }) }
    val tasksDone = taskStatus.count { it }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Üst başlık
            Text(
                text = "DAILY TASKS",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // 2x2 ikon grid ve ortadaki rakam
            Box(contentAlignment = Alignment.Center) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        GoalItem(Icons.Filled.Nightlight, taskStatus[0]) {
                            taskStatus = taskStatus.toMutableList().also { it[0] = !it[0] }
                        }
                        GoalItem(Icons.Filled.FitnessCenter, taskStatus[1]) {
                            taskStatus = taskStatus.toMutableList().also { it[1] = !it[1] }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        GoalItem(Icons.Filled.Alarm, taskStatus[2]) {
                            taskStatus = taskStatus.toMutableList().also { it[2] = !it[2] }
                        }
                        GoalItem(Icons.Filled.LocalDrink, taskStatus[3]) {
                            taskStatus = taskStatus.toMutableList().also { it[3] = !it[3] }
                        }
                    }
                }

                // Ortadaki rakamsal gösterim
                Text(
                    text = "$tasksDone/$totalTasks",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF6F00)
                )
            }
        }
    }
}

@Composable
fun GoalItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    done: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .background(
                if (done) Color(0xFF424242) else Color(0xFF6A1B9A),
                shape = MaterialTheme.shapes.medium
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (done) Color(0xFFFF6F00) else Color.White
        )
    }
}
