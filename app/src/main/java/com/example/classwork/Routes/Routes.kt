package com.example.classwork.Routes

sealed class Routes(val route: String) {
    object Splash: Routes("SplashScreen")
    object SignUp: Routes("SignUp")
    object Login: Routes("Login")
    object Home: Routes("Home")
    object Profile: Routes("Profile")
}