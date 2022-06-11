package com.example.allinscanner.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QrCodeEntity::class, PdfEntity::class], version = 1, exportSchema = false )
abstract class DatabaseAllInScanner : RoomDatabase(){

    abstract fun qrCodeDao():QrCodeDAO

    abstract fun pdfDao():PdfDAO


    companion object{
        const val DATABASE_NAME = "All_in_Scanner_db"
    }
}