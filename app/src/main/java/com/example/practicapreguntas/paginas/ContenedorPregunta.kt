package com.example.practicapreguntas.paginas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.practicapreguntas.R
import com.example.practicapreguntas.ui.theme.PracticaPreguntasTheme

// PONER UN ELEMENTO COMPOSABLE QUE RENDERIZE T0DO, Y UN ELEMENTO NO COMPOSABLE CON LOS DATOS
// DE LA PREGUNTA.

@Composable
fun ContenedorPregunta(
    navController: NavHostController?,
) {
    var lista = arrayListOf(Pregunta("hola",R.drawable.mario_porro,true,false),
        Pregunta("adios",R.drawable.mario_porro,true,false))

    var pregunta by remember { mutableStateOf(lista.get(0)) }

    var resultado by remember { mutableStateOf("") }
    var colorTextoResultado by remember {
        mutableStateOf(Color.White) // TODO poner color definido en res
    }



    fun boolAString(b: Boolean): String {
        if (b) return "verdadero"
        else return "falso"
    }

    // todo colores
    fun checkResultado(respuestaUsuario: Boolean) {
        if (!pregunta.haRespondido) {
            if (pregunta.respuesta == respuestaUsuario) {
                resultado = "Respuesta correcta :D"
                colorTextoResultado = Color.Green
            } else {
                resultado = "MAAAL!!!!! pusiste ${boolAString(respuestaUsuario)} " +
                        "pero era ${boolAString(!respuestaUsuario)}!!!!!"
                colorTextoResultado = Color.Red
            }
        }
       pregunta.haRespondido = true
    }

    Column() {
        Text(
            pregunta.texto, Modifier
                .fillMaxWidth()
        )

        Image(
            painter = painterResource(id = pregunta.idImagen),
            contentDescription = null,
            Modifier.fillMaxWidth()
        )

        // botones true false
        Row(Modifier.fillMaxWidth()) {
            // FALSE
            Button(
                onClick = { checkResultado(false) },
                Modifier.weight(1f),
                shape = RectangleShape

            ) {
                Text(text = "wtf no \uD83D\uDC4E") // &#128078
            }
            // TRUE
            Button(
                onClick = { checkResultado(true) },
                Modifier.weight(1f),
                shape = RectangleShape

            ) {
                Text(text = "factores \uD83D\uDC4D") // &#128077
            }
        }

        // botones anterior siguiente
        Row(Modifier.fillMaxWidth()) {
            // anterior
            Button(
                onClick = { /*TODO*/ },
                Modifier.weight(1f)
            ) {
                Icon(
                    Icons.Rounded.ArrowBack,
                    contentDescription = "Anterior"
                )
            }

            // siguiente
            Button(
                onClick = { pregunta = lista.get(lista.indexOf(pregunta) + 1)},
                Modifier.weight(1f),
            ) {
                Icon(
                    Icons.Rounded.ArrowForward,
                    contentDescription = "Siguiente"
                )
            }

        }
        Text(text = resultado)
    }


}


@Composable
@Preview(showBackground = true)
fun previewPregunta() {
    PracticaPreguntasTheme {
        ContenedorPregunta(
            navController = rememberNavController()
        )
    }
}

