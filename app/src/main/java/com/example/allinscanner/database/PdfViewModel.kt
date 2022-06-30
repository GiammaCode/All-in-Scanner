package com.example.allinscanner.database

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PdfViewModel
@Inject
constructor(
    pdfDAO: PdfDAO
) : ViewModel() {
    val allPdf = mutableListOf<PdfEntity>()

    private val repository: PdfRepository = PdfRepository(pdfDAO)
    init {
        onTriggerEvent(PdfEvent.GetAllPdf)
    }

    fun onTriggerEvent(event: PdfEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (event) {
                    is PdfEvent.GetAllPdf-> {
                        allPdf.addAll(repository.getAllPdf())
                    }
                    is PdfEvent.AddPdf -> {
                        addPdf(event.pdf)
                    }
                }
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }
    }


    fun addPdf(pdf: PdfEntity) {
        viewModelScope.launch {
            repository.addPdf(pdf)
            allPdf.add(pdf)
        }
    }
}