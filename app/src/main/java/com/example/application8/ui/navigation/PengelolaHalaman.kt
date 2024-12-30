package com.example.application8.ui.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.application8.ui.view.DestinasiDetail
import com.example.application8.ui.view.DestinasiEntry
import com.example.application8.ui.view.DestinasiHome
import com.example.application8.ui.view.DestinasiUpdate
import com.example.application8.ui.view.DetailView
import com.example.application8.ui.view.EntryMhsScreen
import com.example.application8.ui.view.HomeScreen
import com.example.application8.ui.view.UpdateView

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        composable(DestinasiHome.route){
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route)},
                onDetailClick = {nim ->
                    navController.navigate("${DestinasiDetail.route}/$nim")
                    println(
                        "PengelolaHalaman: nim = $nim"
                    )
                },
            )
        }
        composable(DestinasiEntry.route){
            EntryMhsScreen(navigateBack = {
                navController.navigate(DestinasiHome.route){
                    popUpTo(DestinasiHome.route){
                        inclusive = true
                    }
                }
            })
        }
        composable("${DestinasiDetail.route}/{nim}") { Detail ->
            val nim = Detail.arguments?.getString("nim") ?: ""
            DetailView(nim = nim, navigateBack = { navController.navigateUp() },
                onEditClick = { nim ->
                    navController.navigate("${DestinasiUpdate.route}/$nim")
                })
        }

        composable("${DestinasiUpdate.route}/{nim}") { Update ->
            val nim = Update.arguments?.getString("nim") ?: ""
            UpdateView(navigateBack = { navController.navigateUp() })
        }
    }
}