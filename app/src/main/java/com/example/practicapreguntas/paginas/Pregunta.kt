package com.example.practicapreguntas.paginas

data class Pregunta(
    val texto: String,
    val idImagen: Int,
    val respuesta: Boolean,
    var haRespondido: Boolean
)