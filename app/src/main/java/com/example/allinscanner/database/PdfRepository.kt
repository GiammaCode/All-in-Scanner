package com.example.allinscanner.database

import javax.inject.Inject

class PdfRepository @Inject constructor(private val pdfDAO: PdfDAO) {
    suspend fun addPdf(newPdf: PdfEntity){
        pdfDAO.insertPdf(newPdf)
    }

    suspend fun getAllPdf():List<PdfEntity>{
        return pdfDAO.getPdf()
    }
}