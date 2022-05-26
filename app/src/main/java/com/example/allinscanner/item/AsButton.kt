package com.example.allinscanner.item

import android.Manifest
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.allinscanner.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AsButton(text : String, ButtonIcon :Int, navController: NavController, newScreen : String){
    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)

    Button(onClick = {
        cameraPermissionState.launchPermissionRequest()
        navController.navigate(newScreen)
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.scanner_red)),
        modifier = Modifier
            .height(70.dp)
            .width(300.dp),
        shape = RoundedCornerShape(50)
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
