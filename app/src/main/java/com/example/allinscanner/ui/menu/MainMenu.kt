package com.example.allinscanner.mainMenu

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
fun MainMenu(navController: NavController) {
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
                    AsButton("Read Qr", R.drawable.ic_baseline_qr_code_scanner_24, navController,"readQR_screen")
                }
                Row(modifier = Modifier.padding(top = 30.dp)) {
                    AsButton("Generate Qr", R.drawable.ic_baseline_qr_code_24, navController,"generateQR_screen")
                }
                Row(modifier = Modifier.padding(top = 30.dp)) {
                    AsButton("Scan PDF", R.drawable.ic_baseline_picture_as_pdf_24, navController, "scanPDF_screen")
                }
                Row(modifier = Modifier.padding(top = 30.dp)) {
                    AsButton("Read barcode", R.drawable.ic_baseline_barcode_24, navController, "readBarcode_screen")
                }
            }
        }},
        bottomBar = { MainBottomBar(navController)}
    )
}