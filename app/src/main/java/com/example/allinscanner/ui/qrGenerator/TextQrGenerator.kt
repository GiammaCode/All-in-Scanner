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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.allinscanner.R
import com.example.allinscanner.item.AsButtonGenerator
import com.example.allinscanner.item.BottomBarForGenQR
import com.example.allinscanner.item.MainBottomBar
import com.example.allinscanner.item.topBarSec
import com.example.allinscanner.ui.qrGenerator.getQrCodeBitmap

@Composable
fun generateQRfromtext(navController : NavController) {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    var qrContent by remember { mutableStateOf("All in Scanner")}
    var bmp by remember { mutableStateOf(getQrCodeBitmap(qrContent))}
    

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { topBarSec("QR generator", navController)},
        content = {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround) {
                    AsButtonGenerator("Text", R.drawable.ic_baseline_text_snippet_24, navController, "textGenerator_Screen")
                    AsButtonGenerator("URL", R.drawable.ic_baseline_add_link_24, navController, "urlGenerator_Screen")
                    AsButtonGenerator("Position", R.drawable.ic_baseline_map_24, navController, "positionGenerator_Screen")
                }
                Row() {
                    //QR CODE image
                        Image(bitmap = bmp.asImageBitmap(), contentDescription = "qr")
               }
                Row (){
                  //manda configurazione
                    //Text("Insert your text")
                    Box(
                        Modifier
                            .padding(12.dp)
                            .fillMaxWidth()
                            .height(100.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.Gray.copy(alpha = 0.6f))
                    ) {
                        TextField(
                            modifier = Modifier.fillMaxSize(),
                            value = qrContent,
                            onValueChange = { qrContent = it },
                            textStyle = TextStyle(color = Color.Black),
                            maxLines = 5
                        )
                    }
                }
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround) {
                    Button(onClick = {
                        //save QR
                    },
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.scanner_red)),
                        modifier = Modifier
                            .height(50.dp)
                            .width(170.dp),
                        shape = RoundedCornerShape(30)
                    )
                    {
                        Image(
                            painterResource(id = R.drawable.ic_baseline_save_alt_24),
                            contentDescription = "Save QR",
                            modifier = Modifier.size(20.dp))
                        Text(text = "Save QR",
                            Modifier.padding(start = 10.dp),
                            style = MaterialTheme.typography.subtitle1)
                    }

                    Button(onClick = {
                        getQrCodeBitmap(qrContent)
                    },
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.scanner_red)),
                        modifier = Modifier
                            .height(50.dp)
                            .width(170.dp),
                        shape = RoundedCornerShape(30)
                    )
                    {
                        Image(
                            painterResource(id = R.drawable.ic_baseline_hive_24),
                            contentDescription = "Generate QR",
                            modifier = Modifier.size(20.dp))
                        Text(text = "Generate QR",
                            Modifier.padding(start = 10.dp),
                            style = MaterialTheme.typography.subtitle1)
                    }
                }
                }
                  },
        bottomBar = { MainBottomBar(navController)}
    )
}



