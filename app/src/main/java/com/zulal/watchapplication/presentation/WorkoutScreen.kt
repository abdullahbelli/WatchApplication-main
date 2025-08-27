package com.zulal.watchapplication.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.material.*
import com.zulal.watchapplication.ui.theme.*

@Composable
fun WorkoutScreen(onNavigate: (String) -> Unit) {
    ScalingLazyColumn(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { Text("WORKOUTS", style = MaterialTheme.typography.title2, color = OrangeAccent) }
        item { Spacer(Modifier.height(8.dp)) }



        item {
            Chip(
                onClick = { onNavigate("steps") },
                label = {Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center // hem yatay hem dikey ortalar
                ) {
                    Text(
                        "Step Counter",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
                },
                colors = ChipDefaults.chipColors(
                    backgroundColor = OrangeAccent, // istediğin arka plan rengi
                    contentColor = Color.Black // içerik rengi (ikon/text varsayılan)
                ),
                modifier = Modifier.size(width = 150.dp, height = 48.dp)
            )
        }

        item {
            Chip(
                onClick = { onNavigate("heart") },
                label = { Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center // hem yatay hem dikey ortalar
                ) {
                    Text(
                        "Heart Rate",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
                },
                colors = ChipDefaults.chipColors(
                    backgroundColor = OrangeAccent, // istediğin arka plan rengi
                    contentColor = Color.Black // içerik rengi (ikon/text varsayılan)
                ),
                modifier = Modifier.size(width = 150.dp, height = 48.dp)
            )
        }

        item {
            Chip(
                onClick = { onNavigate("nutrition") },
                label = { Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center // hem yatay hem dikey ortalar
                ) {
                    Text(
                        "Nutrition",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
                },
                colors = ChipDefaults.chipColors(
                    backgroundColor = OrangeAccent, // istediğin arka plan rengi
                    contentColor = Color.Black // içerik rengi (ikon/text varsayılan)
                ),
                modifier = Modifier.size(width = 150.dp, height = 48.dp)
            )
        }

        item {
            Chip(
                onClick = { onNavigate("schedule") },
                label = { Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center // hem yatay hem dikey ortalar
                ) {
                    Text(
                        "Schedule",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
                },
                colors = ChipDefaults.chipColors(
                    backgroundColor = OrangeAccent, // istediğin arka plan rengi
                    contentColor = Color.Black // içerik rengi (ikon/text varsayılan)
                ),
                modifier = Modifier.size(width = 150.dp, height = 48.dp)
            )
        }

    }
}
