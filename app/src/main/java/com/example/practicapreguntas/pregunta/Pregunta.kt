package com.example.practicapreguntas.pregunta

data class Pregunta(
    val texto: String,
    val idImagen: Int,
    val respuesta: Boolean,
    var haRespondido: Boolean = false
)