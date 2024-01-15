package com.example.classwork.Presentation.Screens.DashboardScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.classwork.Presentation.Components.NavigationBarBottom

@Preview
@Composable
fun HomeScreen(navController: NavController) {
    Column (
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x12, 0x12, 0x12)),
    ){
        NavigationBarBottom(navController)
    }
}