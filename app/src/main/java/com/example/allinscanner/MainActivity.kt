package com.example.allinscanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.allinscanner.mainMenu.firstMenu

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           firstMenu()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
       firstMenu()
}

