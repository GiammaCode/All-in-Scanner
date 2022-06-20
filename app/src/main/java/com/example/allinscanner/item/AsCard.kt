package com.example.allinscanner.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.SemanticsActions.OnClick
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.allinscanner.R
import com.example.allinscanner.ui.qrGenerator.getQrCodeBitmap


@Composable
fun asQrCard(qrName:String?, path: String, type:String?) {
    val openDialog = remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable(
                onClick = {
                openDialog.value = !openDialog.value
            }),
        elevation = 10.dp
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Image(
                painterResource(id = R.drawable.ic_baseline_qr_code_24),
                contentDescription = "qr",
                modifier = Modifier.size(50.dp),
                colorFilter = ColorFilter.tint(color = Color.Black)
            )
        }
        Column(
            modifier = Modifier.padding(start = 90.dp, 15.dp)
        ) {
            Text(
                buildAnnotatedString {
                    append("Qr code Name:  ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color.Red)
                    ) {
                        append("$qrName")
                    }
                }
            )
            Text(
                buildAnnotatedString {
                    append("Type:  ")
                    append("$type")
                }
            )
        }
        if(openDialog.value){
            Box(
                Modifier
                    .size(400.dp,300.dp)
                    .padding(top = 25.dp, start = 20.dp),
                contentAlignment =Alignment.Center
            ) {
                Image(getQrCodeBitmap(path, android.graphics.Color.BLACK).asImageBitmap(), "qr code")
            }
        }

    }
}
@Composable
fun asPdfCard(qrName:String?, path: String?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { },
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(
                buildAnnotatedString {
                    append("Pdf Name:  ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color.Red)
                    ) {
                        append("$qrName")
                    }
                }
            )
            Text(
                buildAnnotatedString {
                    append("path: ")
                    append("$path")
                }
            )
        }
    }
}