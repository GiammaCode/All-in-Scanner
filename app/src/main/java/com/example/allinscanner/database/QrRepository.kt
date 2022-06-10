package com.example.allinscanner.database

import javax.inject.Inject

class QrRepository  @Inject constructor(private val qrDAO: QrCodeDAO){
    suspend fun addQr(newQr: QrCodeEntity){
        qrDAO.insertQr(newQr)
    }

    suspend fun getAllQr(): List<QrCodeEntity>{
        return qrDAO.getQr()
    }
}