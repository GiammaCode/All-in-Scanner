package com.example.allinscanner.screen

import android.content.Context
import android.graphics.Camera
import android.net.Uri
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.allinscanner.R
import com.example.allinscanner.database.PdfViewModel
import com.example.allinscanner.item.AsFloatingButton
import com.example.allinscanner.item.BottomBarForScan
import com.example.allinscanner.item.topBarSec
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import java.io.File

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun scanPDF(navController: NavController, pdfViewModel: PdfViewModel) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    var pdfName by remember { mutableStateOf("") }
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    var imageCapture: ImageCapture? by remember { mutableStateOf(null) }
    var preview by remember { mutableStateOf<Preview?>(null) }
    val executor = ContextCompat.getMainExecutor(context)
    val cameraProvider = cameraProviderFuture.get()
    var camera = cameraProvider.bindToLifecycle(lifecycleOwner, CameraSelector.DEFAULT_BACK_CAMERA)
    var photoFile = File(
        "",
       ""
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { topBarSec("Scanner PDF", navController) },
        //camera button
        floatingActionButton = {
            imageCapture?.let {
                 photoFile = AsFloatingButton(
                    context = context,
                    outputDirectory = context.getDirectory(),
                    onMediaCaptured = { url -> },
                    executor = executor,
                    imageCapture = it,
                    saveName = pdfName
                )
            }
        },
        content = {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { ctx ->
                    val previewView = PreviewView(ctx)
                    cameraProviderFuture.addListener({
                        imageCapture = ImageCapture.Builder()
                            .setTargetRotation(previewView.display.rotation)
                            .build()

                        val cameraSelector = CameraSelector.Builder()
                            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                            .build()

                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            cameraSelector,
                            imageCapture,
                            preview
                        )
                    }, executor)
                    preview = Preview.Builder().build().also {
                        it.setSurfaceProvider(previewView.surfaceProvider)
                    }
                    previewView
                }
            )
            Box(
                Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Black.copy(alpha = 0.6f))
            ) {
                TextField(
                    modifier = Modifier.fillMaxSize(),
                    value = pdfName,
                    onValueChange = { pdfName = it },
                    label = { Text(text = "Insert PDF name:",
                        color = Color.White) },
                    textStyle = TextStyle(color = Color.White),
                    maxLines = 1
                )
            }
        },
        bottomBar = {
            BottomBarForScan(
                navController = navController,
                context = context,
                saveName = pdfName,
                photoUri = Uri.fromFile(photoFile) ,
                outputDirectory = context.getDirectory(),
                camera = camera,
                pdfViewModel = pdfViewModel
            )

        }
    )
}


//Store the capture image
fun Context.getDirectory(): File {
    val mediaDir = this.externalMediaDirs.firstOrNull()?.let {
        File(it, this.resources.getString(R.string.app_name)).apply { mkdirs() }
    }
    return if (mediaDir != null && mediaDir.exists())
        mediaDir else this.filesDir
}



