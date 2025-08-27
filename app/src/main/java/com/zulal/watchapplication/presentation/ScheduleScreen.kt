package com.zulal.watchapplication.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import com.zulal.watchapplication.ui.theme.*

@Composable
fun ScheduleScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("SCHEDULE", style = MaterialTheme.typography.caption1, color = PurpleAccent)
        Spacer(Modifier.height(8.dp))
        Text("Next: Rowing Training", style = MaterialTheme.typography.body1, color = OrangeAccent)
        Text("08:00 - 09:00 AM", style = MaterialTheme.typography.caption2, color = GrayAccent)
    }
}
