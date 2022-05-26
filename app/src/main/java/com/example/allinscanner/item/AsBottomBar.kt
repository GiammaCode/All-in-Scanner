package com.example.allinscanner.item

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.allinscanner.R

@Composable
fun MainBottomBar(){
    BottomAppBar(backgroundColor = colorResource(R.color.scanner_red),
    )
    {
        Text("Bottom App Bar")
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

