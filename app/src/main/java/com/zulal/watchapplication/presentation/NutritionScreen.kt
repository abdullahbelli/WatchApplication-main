package com.zulal.watchapplication.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.ButtonDefaults
import com.zulal.watchapplication.ui.theme.OrangeAccent

@Composable
fun NutritionScreen() {
    var caloriesTaken by remember { mutableStateOf(1250) }
    var calorieGoal by remember { mutableStateOf(2000) }
    var editingGoal by remember { mutableStateOf(false) }
    var addingCalories by remember { mutableStateOf(false) }
    var tempCalories by remember { mutableStateOf(100) }

    val progress = caloriesTaken.toFloat() / calorieGoal

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        // Sol üst köşedeki + butonu
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(12.dp)
                .size(36.dp)
                .background(OrangeAccent, shape = RoundedCornerShape(50))
                .clickable { addingCalories = true },
            contentAlignment = Alignment.Center
        ) {
            Text("+", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }

        when {
            addingCalories -> {
                // Kalori ekleme ekranı
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .height(80.dp)
                            .width(120.dp)
                            .background(Color.DarkGray, shape = MaterialTheme.shapes.medium)
                            .pointerInput(Unit) {
                                detectVerticalDragGestures { change, dragAmount ->
                                    change.consume()
                                    val delta = (dragAmount / 5).toInt()
                                    tempCalories = (tempCalories - delta).coerceIn(50, 2000)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "$tempCalories kcal",
                            color = OrangeAccent,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            caloriesTaken += tempCalories
                            addingCalories = false
                        },
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = OrangeAccent)
                    ) {
                        Text("Add", color = Color.Black)
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Button(
                        onClick = { addingCalories = false },
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = Color.Gray)
                    ) {
                        Text("Cancel", color = Color.White)
                    }
                }
            }

            editingGoal -> {
                // Hedef kalori düzenleme ekranı
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .height(80.dp)
                            .width(120.dp)
                            .background(Color.DarkGray, shape = MaterialTheme.shapes.medium)
                            .pointerInput(Unit) {
                                detectVerticalDragGestures { change, dragAmount ->
                                    change.consume()
                                    val delta = (dragAmount / 5).toInt()
                                    calorieGoal = (calorieGoal - delta).coerceIn(1000, 5000)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "$calorieGoal kcal",
                            color = OrangeAccent,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = { editingGoal = false },
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = OrangeAccent)
                    ) {
                        Text("Set", color = Color.Black)
                    }
                }
            }

            else -> {
                // Normal ekran
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(200.dp)
                ) {
                    CircularProgressIndicator(
                        progress = progress,
                        strokeWidth = 12.dp,
                        color = OrangeAccent,
                        modifier = Modifier.fillMaxSize()
                    )

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "CALORIES",
                            color = OrangeAccent,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            "$caloriesTaken / $calorieGoal",
                            color = OrangeAccent,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable { editingGoal = true }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Alt bilgiler: carbs, protein, fat
                        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                            val infoBoxes = listOf(
                                Triple("125g", "carbs", Color(0xFF29B6F6)),
                                Triple("95g", "protein", Color(0xFFAB47BC)),
                                Triple("45g", "fat", Color(0xFFFFEE58))
                            )
                            infoBoxes.forEach { (value, label, color) ->
                                Box(
                                    modifier = Modifier
                                        .width(60.dp)
                                        .height(50.dp)
                                        .background(Color.DarkGray, shape = MaterialTheme.shapes.medium),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(
                                            value,
                                            color = color,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp
                                        )
                                        Text(
                                            label,
                                            color = Color.White,
                                            fontSize = 12.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

