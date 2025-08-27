package com.zulal.watchapplication

import StepCounterScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.zulal.watchapplication.presentation.*
import com.zulal.watchapplication.ui.theme.WatchAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WatchAppTheme {
                WearApp()
            }
        }
    }
}

@Composable
fun WearApp() {
    val navController = rememberSwipeDismissableNavController()

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Black // 👈 Arka planı sabitle
        ) {
            SwipeDismissableNavHost(
                navController = navController,
                startDestination = "workouts"
            ) {
                composable("workouts") {
                    WorkoutScreen(onNavigate = { route -> navController.navigate(route) })
                }
                composable("steps") { StepCounterScreen() }
                composable("heart") { HeartRateScreen() }
                composable("nutrition") { NutritionScreen() }
                composable("schedule") { ScheduleScreen() }
            }
        }
    }

}
