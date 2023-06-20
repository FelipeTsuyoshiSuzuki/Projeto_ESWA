package com.example.projeto_eswa.navigation

import android.content.pm.PackageManager
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projeto_eswa.ui.home.HomeScreen
import com.example.projeto_eswa.utils.FinishAcitivity

@Composable
fun Navigation(
    finishAcitivity: FinishAcitivity,
    pm: PackageManager
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(finishAcitivity = finishAcitivity, pm = pm, navController = navController)
        }
        composable(
            route = Screen.DetailScreen.route,
            arguments = listOf(
                navArgument("app") {

                }
            )
        ) { Screen.DetailScreen }
    }
}