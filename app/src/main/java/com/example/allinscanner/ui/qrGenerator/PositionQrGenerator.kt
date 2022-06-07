package com.example.allinscanner.ui.qrGenerator

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.allinscanner.item.*
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun generateQRfromPosition(navController: NavController) {
    val context = LocalContext.current

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    var qrContent by remember {
        mutableStateOf("Insert Text")
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

    ///
    val campusCesena = LatLng(44.14, 12.23)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(campusCesena, 10f)
    }
    val myMarker = Marker(
        state = MarkerState(position = campusCesena),
        title = "Campus di Cesena",
        snippet = "Marker in Cesena",
        draggable = true
    ) {
        qrContent = campusCesena.toString()
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
                            .clip(RoundedCornerShape(12.dp)),
                        cameraPositionState = cameraPositionState
                    ){
                        myMarker
                    }
                }
                //insert qrName
                item {
                    //insert qrName
                    OutlinedTextField(
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
                            qrContent = it
                        }
                    )
                }
                //color menu
                item {
                    qrColor = dropDownColor()
                }
                //row of button(save and generate)
                item {
                    var string = "https://maps.google.com/local?q="
                    bmp = AsBottomButtonRow(context, string+campusCesena.latitude+","+campusCesena.longitude, qrName, bmp, qrColor)
                }
                item {
                    Spacer(modifier = Modifier.height(64.dp))
                }
            }
        },
        bottomBar = { MainBottomBar(navController) }
    )
}

