package com.example.allinscanner.item

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.unit.dp

@Composable
fun dropDownColor(): Int {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("Black", "Blue", "Red", "Yellow", "Green", "Purple")
    var selectedIndex by remember { mutableStateOf(0) }
    var color by remember { mutableStateOf("Black") }

    Box(
        modifier = Modifier
            .padding(12.dp)
            .width(75.dp)
            .height(30.dp)
            .clip(RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            items[selectedIndex],
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = { expanded = true })
                .background(Gray.copy(alpha = 0.6f))
                .padding(start = 10.dp)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5))
                .background(Gray.copy(alpha = 0.6f))
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                    color = s
                }) {
                    Text(text = s)
                }
            }
        }
    }
    return convertColor(color)
}

@Composable
private fun convertColor(color: String): Int {
    var colorInt by remember {
        mutableStateOf(Color.BLACK)
    }
    when (color) {
        "Black" -> colorInt = Color.BLACK
        "Blue" -> colorInt = Color.BLUE
        "Red" -> colorInt = Color.RED
        "Yellow" -> colorInt = Color.YELLOW
        "Green" -> colorInt = Color.GREEN
        "Purple" -> colorInt = Color.MAGENTA
    }
    return colorInt
}
