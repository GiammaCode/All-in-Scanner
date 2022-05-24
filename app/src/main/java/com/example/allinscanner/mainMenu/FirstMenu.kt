package com.example.allinscanner.mainMenu

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.allinscanner.R
import com.example.allinscanner.item.AsButton
import com.example.allinscanner.item.AsTopBar

@Composable
fun firstMenu() {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { AsTopBar()},
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
                    AsButton("Read Qr", R.drawable.ic_baseline_qr_code_scanner_24)
                }
                Row(modifier = Modifier.padding(top = 30.dp)) {
                    AsButton("Generate Qr", R.drawable.ic_baseline_qr_code_24)
                }
                Row(modifier = Modifier.padding(top = 30.dp)) {
                    AsButton("Scan PDF", R.drawable.ic_baseline_picture_as_pdf_24)
                }
                Row(modifier = Modifier.padding(top = 30.dp)) {
                    AsButton("Read barcode", R.drawable.ic_baseline_barcode_24)
                }
            }
        }},
        bottomBar = { BottomAppBar(backgroundColor = Color.Red)
        {
            Text("Bottom App Bar")
        } }
    )
}