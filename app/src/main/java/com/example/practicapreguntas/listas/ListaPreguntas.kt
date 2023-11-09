package com.example.practicapreguntas.listas

import com.example.practicapreguntas.Parametros
import com.example.practicapreguntas.R
import com.example.practicapreguntas.data_classes.Pregunta

class ListaPreguntas() {
    companion object {
        fun cargarPreguntas(): ArrayList<Pregunta> {
            var lista = ArrayList<Pregunta>()
            lista.add(ListaPreguntas.pregunta1)
            lista.add(ListaPreguntas.pregunta2)
            lista.add(ListaPreguntas.pregunta3)
            lista.add(ListaPreguntas.pregunta4)
            lista.add(ListaPreguntas.pregunta5)

            if (Parametros.modoAleatorio){
                lista.shuffle()
            }

            return lista
        }


        // todas estas preguntas son 101% objectivas
        val pregunta1 = Pregunta("Mario es canónicamente de Brooklyn",
            R.drawable.mario_porro,
            true)

        val pregunta2 = Pregunta(
            "Sonic Team ha hecho al menos 1 buen juego en los últimos 20 años",
            R.drawable.sanic,
            false // Sonic Mania no es de sonic team ;)
        )
        val pregunta3 = Pregunta(
            "Los juegos \"competitivos\" son una pérdida de tiempo para el 99% de los jugadores",
            R.drawable.door_stuck,
            true // sin comentarios
        )

        val pregunta4 = Pregunta(
            "Existen 5 juegos de la saga principal de Persona",
            R.drawable.persona5,
            false // Persona 2 es una duología, hay 6
        )

        val pregunta5 = Pregunta(
            "Pikachu tiene la punta de la cola negra",
            R.drawable.pikachu_calle,
            false // efecto mandela
        )
    }
}

