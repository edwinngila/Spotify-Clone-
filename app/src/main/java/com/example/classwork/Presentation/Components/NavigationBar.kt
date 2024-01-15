package com.example.classwork.Presentation.Components

import android.view.View.OnClickListener
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.classwork.R


@Composable
fun NavigationBarBottom(navController: NavController) {
       Row (
           horizontalArrangement = Arrangement.SpaceEvenly,
           modifier = Modifier
               .fillMaxWidth()
               .background(Color.Black)
               .height(60.dp)
       ){
           Box(
               modifier = Modifier
                   .width(60.dp)
                   .height(60.dp)
           ){
               Image(
                   painter = painterResource(id = R.drawable.homebtn),
                   contentDescription ="Home Button",
                   modifier = Modifier
                       .width(32.dp)
                       .height(32.dp)
                       .padding(top = 10.dp)
               )
           }
           Box(
               modifier = Modifier
                   .width(60.dp)
                   .height(60.dp)
           ) {
               Image(
                   painter = painterResource(id = R.drawable.magnifyingglass),
                   contentDescription ="Search Button",
                   modifier = Modifier
                       .width(32.dp)
                       .height(32.dp)
                       .padding(top = 10.dp)
                   )
               }
           Box(
               modifier = Modifier
                   .width(60.dp)
                   .height(60.dp)
           ){
               Image(
                   painter = painterResource(id =  R.drawable.username),
                   contentDescription ="Profile Button",
                   modifier = Modifier
                       .width(32.dp)
                       .height(32.dp)
                       .padding(top = 10.dp)
               )
           }
   }
}