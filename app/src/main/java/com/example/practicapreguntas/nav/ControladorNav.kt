package com.example.practicapreguntas.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun ControladorNav(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Rutas.PantallaHome.ruta ){

        composable(Rutas.PantallaHome.ruta){
            /*TODO*/
        }



    }

}