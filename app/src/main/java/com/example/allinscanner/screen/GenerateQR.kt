package com.example.allinscanner.screen

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.allinscanner.item.AsTopBar

@Composable
fun generateQR() {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { AsTopBar() },
        content = {
            Text(text = "generate qr screen")
        },
        bottomBar = { BottomAppBar(backgroundColor = Color.Red)
        {
            Text("Bottom App Bar")
        } }
    )
}