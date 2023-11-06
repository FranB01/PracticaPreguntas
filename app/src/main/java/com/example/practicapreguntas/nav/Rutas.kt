package com.example.practicapreguntas.nav

sealed class Rutas (val ruta : String,){
                    //val nPregunta : Int? = 0
    object PantallaHome : Rutas("Home")
    object ContenedorPregunta : Rutas("Preguntas")



}