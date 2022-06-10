package com.example.allinscanner.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QrCodeDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE) //  INSERIMENTO qrCode
    suspend fun insertQr(qrCode:QrCodeEntity)

    @Query("SELECT * FROM QrCodeEntity")
    suspend fun getQr() : List<QrCodeEntity>
}