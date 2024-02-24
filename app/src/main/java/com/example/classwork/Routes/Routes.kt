package com.example.classwork.Routes

sealed class Routes(val route: String) {
    object Splash: Routes("SplashScreen")
    object SignUp: Routes("SignUp")
    object Login: Routes("Login")
    object Home: Routes("Home")
    object Profile: Routes("Profile")
    object Lybrary:Routes("Lybrary")
    object Search:Routes("Search")
    object EditUserProfile:Routes("EditUserProfile")
    object UploadMusic:Routes("UploadMusic")
    object MusicPlayer:Routes("MusicPlayer/{my_param}")
}