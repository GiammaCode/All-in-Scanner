package com.example.allinscanner.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.allinscanner.R
import com.example.allinscanner.item.BottomBarForGenQR
import com.example.allinscanner.item.MainBottomBar
import com.example.allinscanner.item.topBarFirst
import com.example.allinscanner.item.topBarSec
import com.example.allinscanner.ui.qrGenerator.textConfig

@Composable
fun generateQR(navController : NavController) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val items = listOf("URL", "Text", "Position", "Phone", "SMS", "Email")
    var expanded by remember { mutableStateOf(false) }
    val disabledValue = "B"
    var selectedIndex by remember { mutableStateOf(0) }
    val context = LocalContext.current

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
                Row() {
                    Box(modifier = Modifier
                        .height(50.dp)
                        .width(200.dp)
                        .clickable(onClick = { expanded = true })
                        .background(colorResource(R.color.scanner_red), RoundedCornerShape(15)),
                        contentAlignment = Alignment.Center
                        ){
                        Text(items[selectedIndex])
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier
                                .width(200.dp)
                                .background(
                                    colorResource(R.color.scanner_red),
                                    RoundedCornerShape(5)
                                ),
                        ) {
                            items.forEachIndexed { index, s ->
                                DropdownMenuItem(onClick = {
                                    selectedIndex = index
                                    expanded = false
                                }) {
                                    val disabledText = if (s == disabledValue) {
                                        " (Disabled)"
                                    } else {
                                        ""
                                    }
                                    Text(text = s + disabledText)
                                }
                            }
                        }
            }
        }
                Row() {
                    //QR CODE image
               }
                Row (){
                  //manda configurazione
                    textConfig()
                }
            } },
        bottomBar = { BottomBarForGenQR(navController, context) }
    )
}