package com.example.allinscanner.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.allinscanner.R
import com.example.allinscanner.item.AsButton
import com.example.allinscanner.item.topBarFirst
import com.example.allinscanner.item.MainBottomBar

@Composable
fun generateQR(navController : NavController) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { topBarFirst()},
        content = { Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(vertical = 60.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row(modifier = Modifier.padding(top = 30.dp)) {
                    AsButton("URL", R.drawable.ic_baseline_add_link_24, navController,"readQR_screen")
                }
                Row(modifier = Modifier.padding(top = 30.dp)) {
                    AsButton("Text", R.drawable.ic_baseline_text_snippet_24, navController,"readQR_screen")
                }
                Row(modifier = Modifier.padding(top = 30.dp)) {
                    AsButton("Position", R.drawable.ic_baseline_map_24, navController,"readQR_screen")
                }

            }
        }
        },
        bottomBar = { MainBottomBar(navController) }
    )
}