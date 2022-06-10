package com.example.allinscanner.item
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.allinscanner.controller.Screen
import com.example.allinscanner.mainMenu.MainMenu
import com.example.allinscanner.screen.*
import com.example.allinscanner.ui.databaseScreen.pdfDatabaseScreen
import com.example.allinscanner.ui.databaseScreen.qrDatabaseScreen
import com.example.allinscanner.ui.qrGenerator.generateQRfromDate
import com.example.allinscanner.ui.qrGenerator.generateQRfromPosition
import com.example.allinscanner.ui.qrGenerator.generateQRfromURL

@Composable
 fun Navigation(){
     val navController = rememberNavController()
     NavHost(navController = navController, startDestination = Screen.MainMenu.route) {
         composable(route = Screen.MainMenu.route){
            MainMenu(navController = navController)
         }
         composable(route = Screen.ReadQR.route){
             readQRcode(navController)
         }
         composable(route = Screen.ScanPDF.route){
             scanPDF(navController)
         }
         composable(route = Screen.ReadBarcode.route){
             readBarcode(navController)
         }
         composable(route = Screen.TextQrGenerator.route){
             generateQRfromtext(navController)
         }
         composable(route = Screen.UrlQrGenerator.route){
             generateQRfromURL(navController)
         }
         composable(route = Screen.PositionQrGenerator.route){
             generateQRfromPosition(navController)
         }
         composable(route = Screen.CalendarQrGenerator.route){
             generateQRfromDate(navController)
         }
         composable(route = Screen.QrDataScreen.route){
             qrDatabaseScreen(navController)
         }
         composable(route = Screen.PdfDataScreen.route){
             pdfDatabaseScreen(navController)
         }

     }
 }