package com.example.allinscanner.ui.qrGenerator

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.allinscanner.item.*
import com.google.maps.android.compose.GoogleMap

@Composable
fun generateQRfromPosition(navController: NavController){
    val context = LocalContext.current

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    var qrContent by remember {
        mutableStateOf("Insert Text")
    }
    var qrName by remember {
        mutableStateOf("My QR name")
    }
    var qrColor by remember {
        mutableStateOf(android.graphics.Color.BLACK)
    }
    var bmp by remember {
        mutableStateOf(getQrCodeBitmap(qrContent, qrColor))
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { topBarSec("QR generator", navController) },
        content = {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                //row of button (URL , text, maps)
                item {
                    AsTopButtonRow(navController)
                }
                //QR CODE image
                item {
                    Image(bmp.asImageBitmap(), "qr code")
                }
                //Google Map
                item {
                    GoogleMap(
                        modifier = Modifier
                            .height(450.dp)
                            .fillMaxWidth()
                            .padding(12.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                }
                //insert qrName
                item {
                    Box(
                        Modifier
                            .padding(12.dp)
                            .fillMaxWidth()
                            .height(50.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.Gray.copy(alpha = 0.6f))
                    ) {
                        TextField(
                            modifier = Modifier.fillMaxSize(),
                            value = qrName,
                            onValueChange = { qrName = it },
                            textStyle = TextStyle(color = Color.Black),
                        )
                    }
                }
                //color menu
                item {
                    qrColor = dropDownColor()
                }
                //row of button(save and generate)
                item {
                    bmp = AsBottomButtonRow(context, qrContent, qrName, bmp, qrColor)
                }
                item {
                    Spacer(modifier = Modifier.height(64.dp))
                }
            }
        },
        bottomBar = { MainBottomBar(navController) }
    )
}

