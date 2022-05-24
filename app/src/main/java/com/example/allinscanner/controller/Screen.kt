package com.example.allinscanner.controller

sealed class Screen (val route: String) {
    object MainMenu: Screen(route = "mainMenu_screen")
    object ReadQR : Screen(route = "readQR_screen")
    object GenerateQR : Screen(route = "generateQR_screen")
    object ScanPDF : Screen(route = "scanPDF_screen")
    object ReadBarcode : Screen(route = "readBarcode_screen")
}