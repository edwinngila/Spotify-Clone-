package com.example.classwork

import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.classwork.Presentation.ui.theme.ClassWorkTheme
import com.example.classwork.Controlers.AuthViewModel
import com.example.classwork.Presentation.Screens.AuthScreen.LoginForm
import com.example.classwork.Presentation.Screens.AuthScreen.SignUpForms
import com.example.classwork.Presentation.Screens.SplashScreen.FirstScreen
import com.example.classwork.Routes.OnDemandRoutes
import com.example.classwork.Routes.Routes
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClassWorkTheme {
                OnDemandRoutes()
            }
        }
    }
}

@Composable
fun OnDemandRoutes() {
    val vm: AuthViewModel = hiltViewModel()
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Splash.route ) {
        composable(Routes.Splash.route){ FirstScreen(navController) }
        composable(Routes.SignUp.route){ SignUpForms(navController,vm) }
        composable(Routes.Login.route){ LoginForm(navController,vm) }
    }
}