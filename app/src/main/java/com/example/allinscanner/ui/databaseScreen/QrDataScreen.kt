package com.example.allinscanner.ui.databaseScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.allinscanner.item.MainBottomBar
import com.example.allinscanner.item.topBarSec


@Composable
fun qrDatabaseScreen(navController: NavController) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { topBarSec("My Qr code", navController) },
        content = { Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "qr database Screen")
        }
        },
        bottomBar = { MainBottomBar(navController) }
    )
}