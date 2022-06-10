package com.example.allinscanner.ui.qrGenerator

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.allinscanner.item.*
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

    var qrPosition by remember{
        mutableStateOf(LatLng(44.14, 12.23)
        )}

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(qrPosition, 10f)
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
                        cameraPositionState = cameraPositionState,
                        onMapClick = { p0 -> qrPosition = p0}
                    ){
                        Marker(
                            state = MarkerState(qrPosition),
                            title = "All In Scanner",
                            snippet = "Convert this position",
                            draggable = true,
                            flat = true
                        ) {
                        }

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
                            qrName = it
                        }
                    )
                }
                //color menu
                item {
                    qrColor = dropDownColor()
                }
                //row of button(save and generate)
                item {
                    var qrCont = "https://maps.google.com/local?q="+qrPosition.latitude+","+qrPosition.longitude
                    bmp = AsBottomButtonRow(context, qrCont, qrName, bmp, qrColor)
                }
                item {
                    Spacer(modifier = Modifier.height(64.dp))
                }
            }
        },
        bottomBar = { MainBottomBar(navController) }
    )
}

