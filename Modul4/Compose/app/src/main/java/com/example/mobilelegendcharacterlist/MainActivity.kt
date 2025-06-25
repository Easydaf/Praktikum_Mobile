package com.example.mobilelegendcharacterlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.example.mobilelegendcharacterlist.ui.theme.MobileLegendCharacterListTheme
import com.example.mobilelegendcharacterlist.viewmodel.HeroViewModel
import com.example.mobilelegendcharacterlist.viewmodel.HeroViewModelFactory
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.mobilelegendcharacterlist.ui.screen.DetailScreen
import com.example.mobilelegendcharacterlist.ui.screen.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MobileLegendCharacterListTheme {
                val navController = rememberNavController()

                val viewModel: HeroViewModel = viewModel(
                    factory = HeroViewModelFactory("List Hero ML")
                )

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "heroList"
                    ) {
                        composable("heroList") {
                            HomeScreen(viewModel = viewModel, navController = navController)
                        }
                        composable(
                            route = "penjelasan/{description}/{image}",
                            arguments = listOf(
                                navArgument("description") { type = NavType.StringType },
                                navArgument("image") { type = NavType.IntType }
                            )
                        ) { backStackEntry ->
                            val description = backStackEntry.arguments?.getString("description") ?: ""
                            val image = backStackEntry.arguments?.getInt("image") ?: 0
                            DetailScreen(description = description, image = image)
                        }
                    }
                }
            }
        }
    }
}
