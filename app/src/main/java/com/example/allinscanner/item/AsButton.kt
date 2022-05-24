package com.example.allinscanner.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun AsButton(text : String, ButtonIcon :Int, navController: NavController, newScreen : String){
    Button(onClick = { navController.navigate(newScreen)},
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
        modifier = Modifier
            .height(70.dp)
            .width(300.dp)
    )
    {
        Image(
            painterResource(id = ButtonIcon),
            contentDescription = text,
            modifier = Modifier.size(20.dp))
        Text(text = text,
            Modifier.padding(start = 10.dp),
            style = MaterialTheme.typography.h5)
    }
}