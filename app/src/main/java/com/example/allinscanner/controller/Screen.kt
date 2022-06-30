package com.example.allinscanner.controller

sealed class Screen (val route: String) {
    object MainMenu: Screen(route = "mainMenu_screen")

    object ReadQR : Screen(route = "readQR_screen")
    object ScanPDF : Screen(route = "scanPDF_screen")
    object ReadBarcode : Screen(route = "readBarcode_screen")

    object TextQrGenerator : Screen(route = "textGenerator_screen")
    object UrlQrGenerator : Screen(route = "urlGenerator_screen")
    object PositionQrGenerator : Screen(route = "positionGenerator_screen")
    object CalendarQrGenerator : Screen(route = "calendarGenerator_screen")


    object QrDataScreen : Screen(route = "qrDatabase_screen")
    object QrTextDbScreen : Screen(route = "qrTextDbScreen")
    object QrPosDbScreen : Screen(route = "qrPosDbScreen")
    object QrCalDbScreen : Screen(route = "qrCalDbScreen")
    object QrUrlDbScreen : Screen(route = "qrUrlDbScreen")
    object PdfDataScreen : Screen(route = "pdfDatabase_screen")
}