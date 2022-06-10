package com.example.allinscanner.database

import androidx.room.Database
import androidx.room.RoomDatabase

//andra aggiunta entita pdf
@Database(entities = [QrCodeEntity::class], version = 1, exportSchema = false )
abstract class DatabaseAllInScanner : RoomDatabase(){

    abstract fun qrCodeDao():QrCodeDAO

    //aggiungere fun per pdf

    companion object{
        const val DATABASE_NAME = "All_in_Scanner_db"
    }
}