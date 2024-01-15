package com.example.classwork.Presentation.Screens.DashboardScreen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.classwork.Presentation.Components.BottomNavigationItem
import com.example.classwork.Presentation.Components.BottomNavigationMenu
import com.example.classwork.Presentation.Components.FormInput
import com.example.classwork.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadMusic(navController: NavController) {
    var MusicName = remember { mutableStateOf("") }
    val Discription = remember { mutableStateOf("") }
    Scaffold (
        bottomBar = {
            BottomNavigationMenu(
                selectedItem = BottomNavigationItem.LYBRARY ,
                navController = navController
            )
        }
    ){innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x12, 0x12, 0x12))
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ){
                Image(
                    painter = painterResource(id = R.drawable.sportify),
                    contentDescription ="Logo",
                )
            }
            Column (
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
            ){
                Text(
                    text = "Upload your music",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 25.sp,
                    color = Color(0x1F, 0xDF, 0x64)
                )
                Text(
                    text = "Let people listen to you",
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }
            Column {
                FormInput(nameState =MusicName, lable ="Name" )
                TextField(
                    value = Discription.value,
                    onValueChange = {
                        Discription.value = it
                    },
                    label = {
                        Text(
                            text = "Discription"
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(120.dp)
                )
               Row (
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(10.dp)
               ){
                   val launcher = rememberLauncherForActivityResult(
                       contract = ActivityResultContracts.GetContent()
                   ) {uri: Uri? ->
                       uri?.let {  }
                   }
                   Button(
                       onClick = {launcher.launch("audio/*")},
                       colors = ButtonDefaults.buttonColors(
                           containerColor = Color(0x1F, 0xDF, 0x64),
                           contentColor = Color.White
                       ),
                       modifier = Modifier
                           .fillMaxWidth()
                           .background(Color(0x1F, 0xDF, 0x64))
                   ) {
                       Row(
                           modifier = Modifier.fillMaxWidth(),
                           horizontalArrangement = Arrangement.Center
                       ) {
                           Image(
                               painter = painterResource(id = R.drawable.speaker),
                               contentDescription ="Upload"
                           )
                           Spacer(modifier = Modifier.width(8.dp))
                           Text(
                               text = "Upload Music File",
                               modifier = Modifier.padding(top =5.dp)
                           )
                       }
                   }
               }

                Row (
                    modifier = Modifier.fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.fillMaxWidth()
                            .background(Color(0x1F, 0xDF, 0x64)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0x1F, 0xDF, 0x64),
                            contentColor = Color.White
                        ),

                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.imagegallery),
                                contentDescription ="Upload"
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Upload Music Image",
                                modifier = Modifier.padding(top =5.dp)
                            )
                        }
                    }
                }

                Row (
                    modifier = Modifier.fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.fillMaxWidth()
                            .background(Color(0x1F, 0xDF, 0x64)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0x1F, 0xDF, 0x64),
                            contentColor = Color.White
                        ),

                    ) {
                        Text(text = "Save new music")
                    }
                }
            }
        }
    }
}