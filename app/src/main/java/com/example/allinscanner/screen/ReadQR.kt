package com.example.allinscanner.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.allinscanner.R
import com.example.allinscanner.item.AsButton
import com.example.allinscanner.item.AsTopBar

@Composable
fun readQR() {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { AsTopBar() },
        content = {
                  Text(text = "read qr screen")
        },
        bottomBar = { BottomAppBar(backgroundColor = Color.Red)
        {
            Text("Bottom App Bar")
        } }
    )
}
