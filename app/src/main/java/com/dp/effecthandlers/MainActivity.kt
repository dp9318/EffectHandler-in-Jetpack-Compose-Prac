package com.dp.effecthandlers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var sizeState by remember {
                mutableStateOf(200.dp)
            }
            val size by animateDpAsState(
                targetValue = sizeState,
                tween(1000, 200, LinearOutSlowInEasing), label = ""
//                spring( Spring.DampingRatioHighBouncy,Spring.StiffnessLow)
//                keyframes {
//                    durationMillis = 2000
//                    sizeState at 0 with LinearEasing
//                    sizeState * 1.2F at 800 with FastOutLinearInEasing
//                    sizeState * 2f at 2000
//                }
            )
            val infiniteTransition = rememberInfiniteTransition(label = "")
            val color by infiniteTransition.animateColor(
                initialValue = Color.Red,
                targetValue = Color.Green,
                animationSpec = infiniteRepeatable(
                    tween(durationMillis = 2000),
                    repeatMode = RepeatMode.Reverse
                ), label = ""
            )
            Box(modifier = Modifier
                .size(size)
                .background(color),
                contentAlignment = Alignment.Center){
                Column (
                    verticalArrangement = Arrangement.Center
                ){
                    Button(onClick = {
                            sizeState += 50.dp
                        }  ) {
                        Text("Increased Size!")
                    }
                    Button(onClick = {
                            sizeState -= 50.dp
                        }  ) {
                        Text("Decreased Size!")
                    }
                }

            }
        }
    }
}

