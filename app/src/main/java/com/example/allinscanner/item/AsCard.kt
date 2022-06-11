package com.example.allinscanner.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp


@Composable
fun asQrCard(qrName:String?, path: String?, type:String?) {
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
            Text(
                buildAnnotatedString {
                    append("path: ")
                    append("$path")
                }
            )
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