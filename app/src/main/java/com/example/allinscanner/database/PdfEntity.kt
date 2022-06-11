package com.example.allinscanner.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PdfEntity(
    @PrimaryKey var id: String,
    val name: String?,
    val path: String?
)
