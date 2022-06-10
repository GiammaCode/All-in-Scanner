package com.example.allinscanner.di


import android.content.Context

import androidx.room.Room

import com.example.allinscanner.database.DatabaseAllInScanner
import com.example.allinscanner.database.QrCodeDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)

@Module
class DatabaseModule {
    @Provides
    fun provideQrDao(database : DatabaseAllInScanner): QrCodeDAO {
        return database.qrCodeDao()
    }


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext:Context): DatabaseAllInScanner {
        return Room.databaseBuilder(
                appContext as BaseApplication,
           DatabaseAllInScanner::class.java,
                "All_In_Scanner_db"
        ).build()
    }
}
