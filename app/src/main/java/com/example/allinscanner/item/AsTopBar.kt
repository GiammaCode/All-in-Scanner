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
import androidx.compose.ui.unit.dp

@Composable
fun AsTopBar() {
    TopAppBar( backgroundColor = Color.Red){
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement  =  Arrangement.End) {
            Column(modifier = Modifier.padding(end = 45.dp)) {
                Text("All in Scanner",
                    style = MaterialTheme.typography.h5)
            }
            Column(modifier = Modifier.padding(end = 20.dp)) {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Default.Search,
                        contentDescription = "search icon",
                        modifier = Modifier.size(40.dp))
                }
            }

            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Default.Settings,
                    contentDescription = "settings icon",
                    modifier = Modifier.size(40.dp))
            }
        }
    }
}