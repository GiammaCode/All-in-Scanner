package com.example.allinscanner.item

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.allinscanner.R

@Composable
fun topBarFirst() {
    TopAppBar( backgroundColor = colorResource(R.color.scanner_red)){
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement  =  Arrangement.End) {
            Column(modifier = Modifier.padding(end = 150.dp)) {
                Text("All in Scanner",
                    style = MaterialTheme.typography.h5)
            }
        }
    }
}
@Composable
fun topBarSec(string: String, navController: NavController){
    TopAppBar(
        backgroundColor = colorResource(R.color.scanner_red),
        title = { Text(string) },
        elevation = 4.dp,
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        })
}

@Preview
@Composable
fun previewTopBar(){
    topBarFirst()
}