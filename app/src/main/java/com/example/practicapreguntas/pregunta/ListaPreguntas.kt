package com.example.practicapreguntas.pregunta

import com.example.practicapreguntas.R

class ListaPreguntas() {
    companion object {
        fun cargarPreguntas(): ArrayList<Pregunta> {
            var lista = ArrayList<Pregunta>()
            lista.add(ListaPreguntas.pregunta1)
            lista.add(ListaPreguntas.pregunta2)
            lista.add(ListaPreguntas.pregunta3)

            return lista
        }


        // todas estas preguntas son 101% objectivas
        val pregunta1 = Pregunta("Mario es una mala influencia", R.drawable.mario_porro, true)
        val pregunta2 = Pregunta(
            "Sonic Team ha hecho al menos 1 buen juego en los últimos 20 años",
            R.drawable.sanic,
            false
        )
        val pregunta3 = Pregunta(
            "Los juegos \"competitivos\" son una pérdida de tiempo para el 99% de los jugadores",
            R.drawable.door_stuck,
            true
        )
        //val pregunta4 = Pregunta("Los juegos de pelea son para gente con poco IQ", 1/*TODO*/, false) // trampa para gente pretenciosa
    }
}

