package com.example.allinscanner.database

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QrViewModel
@Inject
constructor(
    qrCodeDAO: QrCodeDAO
) : ViewModel() {

    val allQr = mutableListOf<QrCodeEntity>()

    private val repository: QrRepository = QrRepository(qrCodeDAO)

    init {
        onTriggerEvent(QrCodeEvent.GetAllQr)
    }

    fun onTriggerEvent(event: QrCodeEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (event) {
                    is QrCodeEvent.GetAllQr -> {
                        allQr.addAll(repository.getAllQr())
                    }
                    is QrCodeEvent.AddQr -> {
                        addQr(event.qr)
                    }
                }
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }
    }

    fun addQr(qr: QrCodeEntity) {
        viewModelScope.launch {
            repository.addQr(qr)
            allQr.add(qr)
        }
    }

}