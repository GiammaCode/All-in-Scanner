package com.example.allinscanner.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.allinscanner.R
import com.example.allinscanner.database.QrViewModel
import com.example.allinscanner.item.*
import com.example.allinscanner.ui.qrGenerator.getQrCodeBitmap
import com.pspdfkit.internal.id

@Composable
fun generateQRfromtext(navController: NavController, qrViewModel: QrViewModel) {

    val context = LocalContext.current

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    var qrContent by remember {
        mutableStateOf("")
    }
    var qrName by remember {
        mutableStateOf("")
    }
    var qrColor by remember {
        mutableStateOf(android.graphics.Color.BLACK)
    }
    var bmp by remember {
        mutableStateOf(getQrCodeBitmap("All in Scanner", qrColor))
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { topBarSec("QR generator", navController) },
        content = {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //row of button (URL , text, maps)
               item{ AsTopButtonRow(navController)}
                //QR CODE image
                item{Image(bmp.asImageBitmap(), "qr code")}
                //insert text
                item{OutlinedTextField(
                    value = qrContent,
                    maxLines = 5,
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    label = { Text("Qr content") },
                    placeholder = { Text(text = "Insert your text") },
                    onValueChange = {
                        qrContent = it
                    }
                )}
                //insert qrName
                item{OutlinedTextField(
                    value = qrName,
                    maxLines = 5,
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    label = { Text("Qr name") },
                    placeholder = { Text(text = "Insert qr name") },
                    onValueChange = {
                        qrName = it
                    }
                )}
                //Color menu
                item{qrColor = dropDownColor()}
                //row of button(save and generate)
                item{bmp = AsBottomButtonRow(context, qrContent, qrName, bmp, qrColor, qrViewModel, "Text")}
            }
        },
        bottomBar = { MainBottomBar(navController) }
    )
}



