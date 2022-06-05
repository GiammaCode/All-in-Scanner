package com.example.allinscanner.ui.qrGenerator

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

fun getQrCodeBitmap(qrCodeContent: String,  qrColor : Int): Bitmap {
    val size = 700 //pixels
    val bits = QRCodeWriter().encode(qrCodeContent, BarcodeFormat.QR_CODE, size, size)
    return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
        for (x in 0 until size) {
            for (y in 0 until size) {
                it.setPixel(x, y, if (bits[x, y]) qrColor else Color.WHITE)
            }
        }
    }
}