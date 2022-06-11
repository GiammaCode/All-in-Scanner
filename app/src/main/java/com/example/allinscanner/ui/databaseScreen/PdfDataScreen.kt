package com.example.allinscanner.ui.databaseScreen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.allinscanner.database.PdfViewModel
import com.example.allinscanner.item.MainBottomBar
import com.example.allinscanner.item.asPdfCard
import com.example.allinscanner.item.topBarSec

@Composable
fun pdfDatabaseScreen(navController: NavController, pdfViewModel: PdfViewModel) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { topBarSec("My PDF", navController) },
        content = {
            LazyColumn(){
                items(pdfViewModel.allPdf){
                        pdf -> asPdfCard(pdf.name, pdf.path)
                }
                item(){
                    Spacer(modifier = Modifier.padding(20.dp))
                }
            }
        },
        bottomBar = { MainBottomBar(navController) }
    )
}