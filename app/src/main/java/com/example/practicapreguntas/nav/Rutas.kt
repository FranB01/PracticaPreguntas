package com.example.practicapreguntas.nav

sealed class Rutas (val ruta : String,
                    val nPregunta : Int? = 0){
    object PantallaHome : Rutas("Home",0)

    /*
    object Pregunta1 : Rutas(1)
    object Pregunta2 : Rutas(2)
    object Pregunta3 : Rutas(3)
    object Pregunta4 : Rutas(4)
    object Pregunta5 : Rutas(5)
    object Pregunta1 : Rutas("Pregunta 1", 1)
    object Pregunta2 : Rutas("Pregunta 2", 2)
    object Pregunta3 : Rutas("Pregunta 3", 3)
    object Pregunta4 : Rutas("Pregunta 4", 4)
    object Pregunta5 : Rutas("Pregunta 5", 5)
    */

}