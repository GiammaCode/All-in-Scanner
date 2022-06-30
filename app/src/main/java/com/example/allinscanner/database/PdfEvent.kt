package com.example.allinscanner.database

sealed class PdfEvent{
    data class AddPdf(val pdf: PdfEntity) :PdfEvent()

    object GetAllPdf:PdfEvent()
}
