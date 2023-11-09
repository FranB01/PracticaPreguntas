package com.example.practicapreguntas.data_classes

data class Pregunta(
    val texto: String,
    val idImagen: Int,
    val respuesta: Boolean,
    var haRespondido: Boolean = false
)