package com.example.allinscanner.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PdfDAO{
    @Insert(onConflict = OnConflictStrategy.IGNORE) //  INSERIMENTO PDF
    suspend fun insertPdf(pdfEntity: PdfEntity)

    @Query("SELECT * FROM PdfEntity")
    suspend fun getPdf() : List<PdfEntity>
}