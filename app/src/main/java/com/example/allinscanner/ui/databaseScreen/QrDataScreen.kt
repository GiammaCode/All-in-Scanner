package com.example.allinscanner.ui.databaseScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.allinscanner.database.QrViewModel
import com.example.allinscanner.item.MainBottomBar
import com.example.allinscanner.item.asQrCard
import com.example.allinscanner.item.topBarSec


@Composable
fun qrDatabaseScreen(navController: NavController, qrViewModel: QrViewModel) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { topBarSec("My Qr code", navController) },
        content = {
            LazyColumn(){
                items(qrViewModel.allQr){
                    qr -> asQrCard(qr.name, qr.path, qr.type)
                }
                item(){
                    Spacer(modifier = Modifier.padding(20.dp))
                }
            }
        },
        bottomBar = { MainBottomBar(navController) }
    )
}

