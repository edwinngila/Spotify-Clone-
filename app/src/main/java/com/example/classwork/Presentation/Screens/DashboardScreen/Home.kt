package com.example.classwork.Presentation.Screens.DashboardScreen

import android.annotation.SuppressLint
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.classwork.Controlers.AuthViewModel
import com.example.classwork.Controlers.GetUserMusicViewModel
import com.example.classwork.Presentation.Components.BottomNavigationItem
import com.example.classwork.Presentation.Components.BottomNavigationMenu
import com.example.classwork.Presentation.Components.HomeComponents
import com.example.classwork.R
import com.example.classwork.data.MusicItems
import java.time.LocalTime


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController,viewModel: AuthViewModel,getUserMusicvm: GetUserMusicViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0x12, 0x12, 0x12)),
                title = {
                    Text(
                        text = TimerLogic(),
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 25.sp,
                    )
                },
                actions = {
                    Image(
                        painter = painterResource(id = R.drawable.bell),
                        contentDescription = "Notification",
                        modifier = Modifier
                            .padding(10.dp)
                            .width(25.dp)
                            .height(25.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.u),
                        contentDescription = "Profile",
                        modifier = Modifier
                            .padding(10.dp)
                            .width(25.dp)
                            .height(25.dp)
                            .clickable {
                                navController.navigate("Profile")
                            }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = "Settings",
                        modifier = Modifier
                            .padding(10.dp)
                            .width(25.dp)
                            .height(25.dp)
                    )
                }
            )
        },
        bottomBar = {
            BottomNavigationMenu(
                selectedItem = BottomNavigationItem.HOME,
                navController = navController,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color(0x12, 0x12, 0x12)),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            HomeComponents(navController,getUserMusicvm)
        }
    }
}

@Composable
fun TimerLogic():String {
    var currentTime: LocalTime = LocalTime.now()
    return when {
        currentTime.isBefore(LocalTime.NOON) -> "Good Morning"
        currentTime.isBefore(LocalTime.of(18, 0)) -> "Good Afternoon"
        else -> "Good Evening"
    }
}