package com.example.allinscanner.item
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.allinscanner.controller.Screen
import com.example.allinscanner.mainMenu.MainMenu
import com.example.allinscanner.screen.*

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
         composable(route = Screen.GenerateQR.route){
             generateQR()
         }
         composable(route = Screen.ScanPDF.route){
             ScanPDF()
         }
         composable(route = Screen.ReadBarcode.route){
             readBarcode(navController)
         }

     }
 }