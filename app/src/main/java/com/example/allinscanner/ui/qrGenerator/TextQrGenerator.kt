package com.example.allinscanner.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.example.allinscanner.ui.qrGenerator.getQrCodeBitmap


@Composable
fun generateQRfromtext(navController: NavController) {

    val context = LocalContext.current

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    var qrContent by remember {
        mutableStateOf("Insert Text:")
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
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //row of button (URL , text, maps)
                AsTopButtonRow(navController)

                //QR CODE image
                Image(bmp.asImageBitmap(), "qr code")

                //insert text
                Box(
                    Modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                        .height(85.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.Gray.copy(alpha = 0.6f))
                ) {
                    TextField(
                        modifier = Modifier.fillMaxSize(),
                        value = qrContent,
                        onValueChange = { qrContent = it },
                        textStyle = TextStyle(color = Color.Black),
                    )
                }
                //insert qrName
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

                qrColor = dropDownColor()

                //row of button(save and generate)
                bmp = AsBottomButtonRow(context, qrContent, qrName, bmp, qrColor)
            }
        },
        bottomBar = { MainBottomBar(navController) }
    )
}



