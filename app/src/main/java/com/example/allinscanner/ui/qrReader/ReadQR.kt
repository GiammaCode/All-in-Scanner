package com.example.allinscanner.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.allinscanner.item.topBarFirst
import com.example.allinscanner.item.BottomBarForRead
import com.example.allinscanner.item.qRcodeCameraPreview
import com.example.allinscanner.item.topBarSec
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun readQRcode(navController: NavController) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    var QRcodeVal by rememberSaveable { mutableStateOf("barcode:") }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { topBarSec("Read QR code",navController) },
        content = {
            Surface(color = MaterialTheme.colors.background) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    QRcodeVal = qRcodeCameraPreview()
                }
                Box(
                    Modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.Black.copy(alpha = 0.6f))
                ){
                    Text(modifier = Modifier.padding(15.dp),
                        fontSize = 16.sp,
                        color = Color.White,
                        text = QRcodeVal)
                }

            }
        },
        bottomBar = { BottomBarForRead(navController, QRcodeVal)}
    )
}
