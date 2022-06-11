package com.example.allinscanner.database

sealed class QrCodeEvent{
    data class AddQr(val qr: QrCodeEntity):QrCodeEvent()

    object GetAllQr:QrCodeEvent()
}
