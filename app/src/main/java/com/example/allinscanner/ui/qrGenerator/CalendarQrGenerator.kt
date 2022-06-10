package com.example.allinscanner.ui.qrGenerator

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.allinscanner.R
import com.example.allinscanner.database.QrViewModel
import com.example.allinscanner.item.*
import java.util.*


@Composable
fun generateQRfromDate(navController: NavController, qrViewModel: QrViewModel) {

    val context = LocalContext.current

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    var date by remember {
        mutableStateOf("")
    }
    var qrName by remember {
        mutableStateOf("")
    }
    var qrColor by remember {
        mutableStateOf(Color.BLACK)
    }
    var bmp by remember {
        mutableStateOf(getQrCodeBitmap("All in Scanner", qrColor))
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { topBarSec("QR generator", navController) },
        content = {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //row of button (URL , text, maps)
                AsTopButtonRow(navController)
                //QR CODE image
                Image(bmp.asImageBitmap(), "qr code")
                //insert Calendar
                date = showDatePicker(context)
                //insert qrName
                OutlinedTextField(
                    value = qrName,
                    maxLines = 5,
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    label = { Text("Qr name") },
                    placeholder = { Text(text = "Insert qr name") },
                    onValueChange = {
                        qrName = it
                    }
                )
                //Color menu
                qrColor = dropDownColor()
                //row of button(save and generate)
                bmp = AsBottomButtonRow(context, date, qrName, bmp, qrColor)
            }
        },
        bottomBar = { MainBottomBar(navController) }
    )
}

@Composable
fun showDatePicker(context: Context) : String{

    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth/$month/$year"
        }, year, month, day
    )
        Text(text = "Selected Date: ${date.value}")
        Button(
            onClick = {
                datePickerDialog.show()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.scanner_red)),
            modifier = Modifier
                .height(50.dp)
                .width(170.dp),
            shape = RoundedCornerShape(30)
        )
        {
            Text(text = "Open Calendar")
        }
        return date.value
    }






