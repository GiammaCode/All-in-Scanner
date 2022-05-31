package com.example.allinscanner.item

import android.content.Context
import android.hardware.camera2.CameraManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavController
import com.example.allinscanner.R
import com.example.allinscanner.ui.PDFscanner.processPdf
import java.io.File

@Composable
fun MainBottomBar(navController: NavController){
    BottomAppBar(backgroundColor = colorResource(R.color.scanner_red)){
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement  =  Arrangement.Center) {
            val context = LocalContext.current
            val uriHandler = LocalUriHandler.current
            Column(modifier = Modifier.padding(end = 60.dp)) {
                IconButton(onClick = {
                    //navController.navigate("myPDF_screen")
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_picture_as_pdf_24 ),
                        contentDescription = "My pdf icon",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
            Column(modifier = Modifier.padding(end = 20.dp)) {
                IconButton(onClick = {
                    navController.navigate("mainMenu_screen")
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_home_24 ),
                        contentDescription = "home icon",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
            Column(modifier = Modifier.padding(start = 60.dp)) {
                IconButton(onClick = {
                    //navController.navigate("myQr_screen")
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_qr_code_24),
                        contentDescription = "My qr icon",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun BottomBarForRead(navController: NavController, value: String){
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    BottomAppBar(backgroundColor = colorResource(R.color.scanner_red)){
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement  =  Arrangement.Center) {
        val context = LocalContext.current
        val uriHandler = LocalUriHandler.current
        Column(modifier = Modifier.padding(end = 60.dp)) {
            IconButton(onClick = {
                if(value.isNotEmpty()){
                    uriHandler.openUri(value)
                }
                else{
                    Toast.makeText(context,"try scanning again", Toast.LENGTH_SHORT).show()
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_search_in_web_24 ),
                    contentDescription = "searc web icon",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        Column(modifier = Modifier.padding(end = 20.dp)) {
            IconButton(onClick = {
                navController.navigate("mainMenu_screen")
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_home_24 ),
                    contentDescription = "home icon",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        Column(modifier = Modifier.padding(start = 60.dp)) {
            IconButton(onClick = {
                if(value.isNotEmpty()){
                    clipboardManager.setText(AnnotatedString(value))
                    Toast.makeText(context,"Copied succesfully", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(context,"try scanning again", Toast.LENGTH_SHORT).show()
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_content_copy_24 ),
                    contentDescription = "copy icon",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        }
    }
}

@Composable
fun BottomBarForScan(navController: NavController,
                     context: Context,
                     saveName: String,
                     photoUri : Uri,
                     outputDirectory: File
){

            BottomAppBar(backgroundColor = colorResource(R.color.scanner_red)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(modifier = Modifier.padding(end = 60.dp)) {
                    IconButton(onClick = {
                            try {
                                //save the PDF
                                processPdf(context, photoUri, saveName, outputDirectory)
                            } catch (e: Exception) {
                                Toast.makeText(context, "Retry to scan", Toast.LENGTH_SHORT).show()
                                Log.e("processPDF ERROR", e.message.toString())
                            }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_save_alt_24),
                            contentDescription = "save PDF icon",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
                Column(modifier = Modifier.padding(end = 20.dp)) {
                    IconButton(onClick = {
                        navController.navigate("mainMenu_screen")
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_home_24),
                            contentDescription = "home icon",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
                Column(modifier = Modifier.padding(start = 60.dp)) {
                    IconButton(onClick = {
                        Toast.makeText(context, "flash clicked (WIP)", Toast.LENGTH_SHORT).show()

                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_flash_on_24),
                            contentDescription = "flash camera icon",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            }
        }
    }



