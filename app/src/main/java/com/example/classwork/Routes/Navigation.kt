package com.example.classwork.Routes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.classwork.Controlers.AuthViewModel
import com.example.classwork.Presentation.Components.NotificationMessage
import com.example.classwork.Presentation.Components.ProgressSpinner
import com.example.classwork.Presentation.Screens.AuthScreen.LoginForm
import com.example.classwork.Presentation.Screens.AuthScreen.SignUpForms
import com.example.classwork.Presentation.Screens.DashboardScreen.EditUserProfile
import com.example.classwork.Presentation.Screens.DashboardScreen.HomeScreen
import com.example.classwork.Presentation.Screens.DashboardScreen.LybraryScreen
import com.example.classwork.Presentation.Screens.DashboardScreen.MusicPayer
import com.example.classwork.Presentation.Screens.DashboardScreen.SearchScreen
import com.example.classwork.Presentation.Screens.DashboardScreen.UploadMusic
import com.example.classwork.Presentation.Screens.DashboardScreen.UserProfile
import com.example.classwork.Presentation.Screens.SplashScreen.FirstScreen

@Composable
fun OnDemandRoutes() {
    val vm: AuthViewModel = hiltViewModel()
    val navController = rememberNavController()
    ProgressSpinner()
    NotificationMessage(vm = vm)
    NavHost(navController = navController, startDestination = Routes.Splash.route ) {
        composable(Routes.Splash.route){ FirstScreen(navController) }
        composable(Routes.SignUp.route){ SignUpForms(navController,vm) }
        composable(Routes.Login.route){ LoginForm(navController,vm) }

        composable(Routes.Home.route){ HomeScreen(navController,vm)}
        composable(Routes.Profile.route){ UserProfile(navController,vm) }
        composable(Routes.Lybrary.route){ LybraryScreen(navController,vm)}
        composable(Routes.Search.route){ SearchScreen(navController,vm)}
        composable(Routes.EditUserProfile.route){ EditUserProfile(navController,vm)}
        composable(Routes.UploadMusic.route){ UploadMusic(navController)}
        composable(Routes.MusicPlayer.route){ MusicPayer(navController)}
    }
}