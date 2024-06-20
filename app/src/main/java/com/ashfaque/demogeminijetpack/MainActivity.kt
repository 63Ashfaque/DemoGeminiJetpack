package com.ashfaque.demogeminijetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ashfaque.demogeminijetpack.ui.screens.MainScreen
import com.ashfaque.demogeminijetpack.ui.screens.Screen2
import com.ashfaque.demogeminijetpack.ui.theme.DarkGray
import com.ashfaque.demogeminijetpack.ui.theme.DemoGeminiJetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoGeminiJetpackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = DarkGray
                ) {
                    MyApp()
                }
            }
        }
    }

}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "screen1") {
        composable("screen1") { MainScreen(navController) }
        composable("screen2") { Screen2(navController) }
    }
}



