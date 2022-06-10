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
fun pdfDatabaseScreen(navController: NavController) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { topBarSec("My PDF", navController) },
        content = { Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "pdf database Screen")
        }
        },
        bottomBar = { MainBottomBar(navController) }
    )
}