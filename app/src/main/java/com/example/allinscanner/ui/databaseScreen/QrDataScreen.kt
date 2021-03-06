package com.example.allinscanner.ui.databaseScreen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.allinscanner.database.QrCodeEntity
import com.example.allinscanner.database.QrViewModel
import com.example.allinscanner.item.*


@Composable
fun qrDatabaseScreen(navController: NavController, qrViewModel: QrViewModel) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    var localList = qrViewModel.allQr

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { topBarSec("My Qr code", navController) },
        content = {
            LazyColumn(){
                item{
                    Spacer(modifier = Modifier.padding(10.dp))
                    AsTopButtonRowForDb(navController)
                    Spacer(modifier = Modifier.padding(5.dp))
                }
                items(localList){
                    qr ->
                    qr.content?.let { it1 -> asQrCard(qr.name, it1, qr.type) }
                }
                item{
                    Spacer(modifier = Modifier.padding(20.dp))
                }
            }
        },
        bottomBar = { MainBottomBar(navController) }
    )
}

