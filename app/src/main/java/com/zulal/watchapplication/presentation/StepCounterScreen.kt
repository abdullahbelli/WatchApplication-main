import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zulal.watchapplication.ui.theme.*

@Composable
fun StepCounterScreen() {
    var stepsTaken by remember { mutableStateOf(7899) }
    var stepGoal by remember { mutableStateOf(10000) }
    var editingGoal by remember { mutableStateOf(false) }
    val progress = stepsTaken.toFloat() / stepGoal

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        if (!editingGoal) {
            // Normal ekran: progress bar ve alt boxlar
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
                        "STEPS",
                        color = OrangeAccent,
                        fontSize = 14.sp,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        "$stepsTaken / $stepGoal",
                        color = OrangeAccent,
                        fontSize = 28.sp,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                        modifier = Modifier.clickable { editingGoal = true }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Alt bilgiler: km, cal, daily
                    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                        val infoBoxes = listOf(
                            Pair("6.3", "km"),
                            Pair("316", "cal"),
                            Pair("+12%", "daily")
                        )
                        infoBoxes.forEachIndexed { index, (value, label) ->
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(Color.DarkGray, shape = MaterialTheme.shapes.medium),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        value,
                                        color = if (index == 2) Color(0xFF00FF00) else androidx.compose.ui.graphics.Color.White,
                                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                                    )
                                    Text(label, color = androidx.compose.ui.graphics.Color.White, fontSize = 12.sp)
                                }
                            }
                        }
                    }
                }
            }
        } else {
            // Hedef adım düzenleme modu: sadece kaydırmalı seçim ve set butonu
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
                                stepGoal = (stepGoal - delta).coerceIn(1000, 50000)
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "$stepGoal",
                        color = OrangeAccent,
                        fontSize = 28.sp,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { editingGoal = false },
                    colors = ButtonDefaults.buttonColors(containerColor = OrangeAccent)
                ) {
                    Text("Set", color = androidx.compose.ui.graphics.Color.Black)
                }
            }
        }
    }
}

