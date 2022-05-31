package com.example.allinscanner.ui.qrGenerator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun textConfig(){
    var outputText by remember { mutableStateOf("") }
    Column(Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Insert your text")
        Box(
            Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.Gray.copy(alpha = 0.6f))
        ) {
            TextField(
                modifier = Modifier.fillMaxSize(),
                value = outputText,
                onValueChange = { outputText = it },
                textStyle = TextStyle(color = Color.Black),
                maxLines = 5
            )
        }
    }
}

@Composable
fun urlConfig(){
    var outputText by remember { mutableStateOf("") }
    Column(Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Insert your URL")
        Box(
            Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.Gray.copy(alpha = 0.6f))
        ) {
            TextField(
                modifier = Modifier.fillMaxSize(),
                value = outputText,
                onValueChange = { outputText = it },
                textStyle = TextStyle(color = Color.Black),
                maxLines = 1
            )
        }
    }
}