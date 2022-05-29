package com.example.allinscanner.item

import android.Manifest
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.RectF
import android.net.Uri
import android.widget.Toast
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.allinscanner.R
import com.example.allinscanner.ui.PDFscanner.createPdfFromImageTask
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.pspdfkit.document.processor.NewPage
import com.pspdfkit.document.processor.PageImage
import com.pspdfkit.document.processor.PdfProcessor
import com.pspdfkit.document.processor.PdfProcessorTask
import com.pspdfkit.utils.Size
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AsButton(text : String, ButtonIcon :Int, navController: NavController, newScreen : String
){
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

@Composable
fun AsFloatingButton(context: Context,
                     outputDirectory: File,
                     onMediaCaptured: (Uri?) -> Unit,
                     executor: Executor,
                     imageCapture: ImageCapture,
                     saveName : String
): File {
    val saveOutName = saveName + ".png"
    val photoFile = File(
        outputDirectory,
        saveOutName
    )

    Button(
        onClick = {
            if (saveName.isEmpty()) {
                Toast.makeText(context, "Insert PDF name", Toast.LENGTH_SHORT).show()
            } else {
                val imgCapture = imageCapture ?: return@Button
                val photoUri = Uri.fromFile(photoFile)
                val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
                imgCapture.takePicture(
                    outputOptions,
                    executor,
                    object : ImageCapture.OnImageSavedCallback {
                        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                            onMediaCaptured(photoUri)
                        }

                        override fun onError(exception: ImageCaptureException) {
                            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }
        },
        modifier = Modifier
            .size(70.dp)
            .background(colorResource(R.color.scanner_red), CircleShape)
            .clip(CircleShape)
            .border(2.dp, Color.White, CircleShape),
        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.scanner_red)),

        ) {
        Image(
            painterResource(R.drawable.ic_baseline_document_scanner_24),
            contentDescription = "scan button",
            modifier = Modifier.size(20.dp)
        )
    }
    return photoFile
}






