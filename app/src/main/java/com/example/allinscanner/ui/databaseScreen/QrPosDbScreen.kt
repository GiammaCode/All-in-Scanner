package com.example.allinscanner.ui.databaseScreen

import android.util.Log
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
import com.example.allinscanner.database.QrCodeEntity
import com.example.allinscanner.database.QrViewModel
import com.example.allinscanner.item.AsTopButtonRowForDb
import com.example.allinscanner.item.MainBottomBar
import com.example.allinscanner.item.asQrCard
import com.example.allinscanner.item.topBarSec


@Composable
fun qrPosDbScreen(navController: NavController, qrViewModel: QrViewModel) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    var localList = changeList("Position", qrViewModel.allQr)

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

private fun changeList(type: String, allQr: MutableList<QrCodeEntity>) : MutableList<QrCodeEntity>{
    var selectList = mutableListOf<QrCodeEntity>()
    allQr.forEach {
        //Log.d("${it.type.toString()}", "$type")
        if (it.type.toString() == type){
            selectList.add(it)
        }
    }
    Log.d("listFUNZIONE","$selectList")
    return selectList
}

