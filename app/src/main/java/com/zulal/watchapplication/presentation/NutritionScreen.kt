package com.zulal.watchapplication.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import com.zulal.watchapplication.ui.theme.*

@Composable
fun NutritionScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("NUTRITION", style = MaterialTheme.typography.caption1, color = GreenAccent)
        Spacer(Modifier.height(4.dp))
        Text("1250 / 2200 cal", style = MaterialTheme.typography.title2, color = GreenAccent)

        Spacer(Modifier.height(12.dp))
        Text("Carbs: 125g | Protein: 95g | Fat: 45g", style = MaterialTheme.typography.caption2, color = GrayAccent)

        Spacer(Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = 0.55f,
            modifier = Modifier.fillMaxWidth(),
            color = GreenAccent
        )
    }
}
