package com.example.allinscanner.item

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.allinscanner.R
import com.example.allinscanner.screen.getDirectory
import com.example.allinscanner.ui.qrGenerator.getQrCodeBitmap
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.Executor


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AsButton(
    text: String,
    ButtonIcon: Int,
    navController: NavController,
    newScreen: String
) {
    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)

    Button(
        onClick = {
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
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = text,
            Modifier.padding(start = 10.dp),
            style = MaterialTheme.typography.h5
        )
    }
}

@Composable
fun AsButtonGenerator(
    text: String,
    ButtonIcon: Int,
    navController: NavController,
    newScreen: String
) {

    Button(
        onClick = {
            navController.navigate(newScreen)
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.scanner_red)),
        modifier = Modifier
            .height(40.dp)
            .width(150.dp),
        shape = RoundedCornerShape(30)
    )
    {
        Image(
            painterResource(id = ButtonIcon),
            contentDescription = text,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = text,
            Modifier.padding(start = 10.dp),
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Composable
fun AsFloatingButton(
    context: Context,
    outputDirectory: File,
    onMediaCaptured: (Uri?) -> Unit,
    executor: Executor,
    imageCapture: ImageCapture,
    saveName: String
): File {
    val saveOutName = saveName + ".png"
    val photoFile = File(
        outputDirectory,
        saveOutName
    )

    Button(
        onClick = {
            if (saveName.isNotEmpty()) {
                val imgCapture = imageCapture ?: return@Button
                val photoUri = Uri.fromFile(photoFile)
                val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
                imgCapture.takePicture(
                    outputOptions,
                    executor,
                    object : ImageCapture.OnImageSavedCallback {
                        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                            onMediaCaptured(photoUri)
                            Toast.makeText(context, "Image scanned", Toast.LENGTH_SHORT).show()
                        }

                        override fun onError(exception: ImageCaptureException) {
                            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                )
            } else {
                Toast.makeText(context, "Insert PDF name", Toast.LENGTH_SHORT).show()
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

@Composable
fun AsTopButtonRow(
    navController: NavController
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column() {
            AsButtonGenerator(
                "Text",
                R.drawable.ic_baseline_text_snippet_24,
                navController,
                "textGenerator_Screen"
            )
            Spacer(modifier = Modifier.padding(5.dp))
            AsButtonGenerator(
                "URL",
                R.drawable.ic_baseline_add_link_24,
                navController,
                "urlGenerator_Screen"
            )
        }
        Column() {
            AsButtonGenerator(
                "Position",
                R.drawable.ic_baseline_map_24,
                navController,
                "positionGenerator_Screen"
            )
            Spacer(modifier = Modifier.padding(5.dp))
            AsButtonGenerator(
                "Calendar",
                R.drawable.ic_baseline_calendar_month_24,
                navController,
                "calendarGenerator_screen"
            )
        }
    }
}

@Composable
fun AsBottomButtonRow(
    context: Context,
    qrContent: String,
    qrName: String,
    bmp: Bitmap,
    qrColor: Int
): Bitmap {

    var bitmap by remember {
        mutableStateOf(bmp)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        Button(
            onClick = {
                //save QR
                if (qrName.isNotEmpty()) {
                    var filePath = context.getDirectory()
                    var file = File(filePath, "$qrName.png");
                    var fOut = FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 85, fOut);
                    fOut.flush();
                    fOut.close();
                    Toast.makeText(context, "QR code saved with successfully", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(context, "Insert QR code name", Toast.LENGTH_SHORT).show()
                }

            },
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.scanner_red)),
            modifier = Modifier
                .height(50.dp)
                .width(170.dp),
            shape = RoundedCornerShape(30)
        )
        {
            Image(
                painterResource(id = R.drawable.ic_baseline_save_alt_24),
                contentDescription = "Save QR",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "Save QR",
                Modifier.padding(start = 10.dp),
                style = MaterialTheme.typography.subtitle1
            )
        }

        Button(
            onClick = {
                bitmap = getQrCodeBitmap(qrContent, qrColor)
                Toast.makeText(context, "QR code generated with successfully", Toast.LENGTH_SHORT)
                    .show()

            },
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.scanner_red)),
            modifier = Modifier
                .height(50.dp)
                .width(170.dp),
            shape = RoundedCornerShape(30)
        )
        {
            Image(
                painterResource(id = R.drawable.ic_baseline_hive_24),
                contentDescription = "Generate QR",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "Generate QR",
                Modifier.padding(start = 10.dp),
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
    return bitmap
}






