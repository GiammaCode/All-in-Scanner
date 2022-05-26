package com.example.allinscanner.item

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.allinscanner.R

@Composable
fun AsTopBar() {
    TopAppBar( backgroundColor = colorResource(R.color.scanner_red)){
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement  =  Arrangement.End) {
            Column(modifier = Modifier.padding(end = 45.dp)) {
                Text("All in Scanner",
                    style = MaterialTheme.typography.h4)
            }
            Column(modifier = Modifier.padding(end = 10.dp)) {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Default.Search,
                        contentDescription = "search icon",
                        modifier = Modifier.size(30.dp))
                }
            }

            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Default.Settings,
                    contentDescription = "settings icon",
                    modifier = Modifier.size(30.dp))
            }
        }
    }
}

@Preview
@Composable
fun previewTopBar(){
    AsTopBar()
}