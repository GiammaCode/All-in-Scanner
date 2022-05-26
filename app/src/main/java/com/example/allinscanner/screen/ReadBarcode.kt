package com.example.allinscanner.screen

import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.allinscanner.QRcode.BarCodeAnalyser
import com.example.allinscanner.item.AsTopBar
import com.example.allinscanner.item.BarcodeCameraPreview
import com.example.allinscanner.item.BottomBarForRead
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.common.util.concurrent.ListenableFuture
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun readBarcode(navController: NavController) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    var barcodeVal by rememberSaveable { mutableStateOf("barcode:") }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { AsTopBar() },
        content = {
            Surface(color = MaterialTheme.colors.background) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                  barcodeVal = BarcodeCameraPreview()
                }
                Box(Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Black.copy(alpha = 0.6f))
                ){
                    Text(modifier = Modifier.padding(15.dp),
                        fontSize = 16.sp,
                        color = Color.White,
                        text = barcodeVal)
                }

            }
        },
        bottomBar = { BottomBarForRead(navController, barcodeVal)}
    )
}
