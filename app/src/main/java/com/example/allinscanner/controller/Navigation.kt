package com.example.allinscanner.item
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.allinscanner.controller.Screen
import com.example.allinscanner.database.PdfViewModel
import com.example.allinscanner.database.QrViewModel
import com.example.allinscanner.mainMenu.MainMenu
import com.example.allinscanner.screen.*
import com.example.allinscanner.ui.databaseScreen.*
import com.example.allinscanner.ui.qrGenerator.generateQRfromDate
import com.example.allinscanner.ui.qrGenerator.generateQRfromPosition
import com.example.allinscanner.ui.qrGenerator.generateQRfromURL

@Composable
 fun Navigation(){
     val navController = rememberNavController()
     val qrVModel = hiltViewModel<QrViewModel>()
    val pdfVModel = hiltViewModel<PdfViewModel>()
     NavHost(navController = navController, startDestination = Screen.MainMenu.route) {
         composable(route = Screen.MainMenu.route){
            MainMenu(navController = navController)
         }
         composable(route = Screen.ReadQR.route){
             readQRcode(navController)
         }
         composable(route = Screen.ScanPDF.route){
             scanPDF(navController, pdfVModel)
         }
         composable(route = Screen.ReadBarcode.route){
             readBarcode(navController)
         }
         composable(route = Screen.TextQrGenerator.route){
             generateQRfromtext(navController, qrVModel)
         }
         composable(route = Screen.UrlQrGenerator.route){
             generateQRfromURL(navController, qrVModel)
         }
         composable(route = Screen.PositionQrGenerator.route){
             generateQRfromPosition(navController, qrVModel)
         }
         composable(route = Screen.CalendarQrGenerator.route){
             generateQRfromDate(navController, qrVModel)
         }
         composable(route = Screen.QrDataScreen.route){
             qrDatabaseScreen(navController, qrVModel)
         }
         composable(route = Screen.PdfDataScreen.route){
             pdfDatabaseScreen(navController, pdfVModel)
         }
         composable(route = Screen.QrTextDbScreen.route){
             qrTextDbScreen(navController, qrVModel)
         }
         composable(route = Screen.QrPosDbScreen.route){
             qrPosDbScreen(navController, qrVModel)
         }
         composable(route = Screen.QrCalDbScreen.route){
             qrCalDbScreen(navController, qrVModel)
         }
         composable(route = Screen.QrUrlDbScreen.route){
             qrUrlDbScreen(navController, qrVModel)
         }

     }
 }