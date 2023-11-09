package com.example.practicapreguntas.paginas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.practicapreguntas.Parametros
import com.example.practicapreguntas.R
import com.example.practicapreguntas.data_classes.Dialogo
import com.example.practicapreguntas.listas.ListaPreguntas
import com.example.practicapreguntas.ui.theme.Blanco
import com.example.practicapreguntas.ui.theme.Gris
import com.example.practicapreguntas.ui.theme.PracticaPreguntasTheme
import com.example.practicapreguntas.ui.theme.Rojo
import com.example.practicapreguntas.ui.theme.Verde

// PONER UN ELEMENTO COMPOSABLE QUE RENDERIZE T0DO, Y UN ELEMENTO NO COMPOSABLE CON LOS DATOS
// DE LA PREGUNTA.

@Composable
fun ContenedorPregunta(
    navController: NavHostController?,
) {
    /*
    var lista = arrayListOf(
        Pregunta("hola", R.drawable.mario_porro, true, false),
        Pregunta("adios", R.drawable.mario_porro, true, false)
    )
    */

    var lista = ListaPreguntas.cargarPreguntas()
    var pregunta by remember { mutableStateOf(lista.get(0)) }
    var resultado by remember { mutableStateOf("") }

    var colorTextoResultado by remember {
        mutableStateOf(Blanco)
    }
    var colorBotonTrue by remember {
        mutableStateOf(Gris)
    }
    var colorBotonFalse by remember {
        mutableStateOf(Gris)
    }

    var dialogoVisible by remember { mutableStateOf(false) }
    var dialogo by remember { mutableStateOf(
        Dialogo("", R.drawable.mario_porro,)
    ) }

    fun boolAString(b: Boolean): String {
        if (b) return "verdadero"
        else return "falso"
    }

    fun checkResultado(respuestaUsuario: Boolean) {
        if (!pregunta.haRespondido) {
            if (pregunta.respuesta == respuestaUsuario) {
                resultado = "Respuesta correcta :D"
                colorTextoResultado = Verde
            } else {
                resultado = "MAAAL!!!!! pusiste ${boolAString(respuestaUsuario)} " +
                        "pero era ${boolAString(!respuestaUsuario)}!!!!!"
                colorTextoResultado = Rojo
            }
            // colores botones
            // si verde, no rojo
            if (pregunta.respuesta) {
                colorBotonTrue = Verde
                colorBotonFalse = Rojo
            } else {
                colorBotonTrue = Rojo
                colorBotonFalse = Verde
            }
        }
        pregunta.haRespondido = true
    }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            pregunta.texto, Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )

        Image(
            painter = painterResource(id = pregunta.idImagen),
            contentDescription = null,
            Modifier.fillMaxWidth()
        )

        Text(text = resultado)

        // BOTONES
        Column() {
            // botones true false
            Row(Modifier.fillMaxWidth()) {
                // FALSE
                Button(
                    onClick = { checkResultado(false) },
                    Modifier.weight(1f),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = colorBotonFalse),

                    ) {
                    Text(text = "wtf no \uD83D\uDC4E") // &#128078
                }
                // TRUE
                Button(
                    onClick = { checkResultado(true) },
                    Modifier.weight(1f),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = colorBotonTrue),

                    ) {
                    Text(text = "factores \uD83D\uDC4D") // &#128077
                }
            }

            // botones anterior siguiente
            Row(Modifier.fillMaxWidth()) {
                // anterior
                Button(
                    onClick = {
                        if (lista.indexOf(pregunta) != 0) {
                            pregunta = lista[lista.indexOf(pregunta) - 1]
                            resultado = ""
                        } else {
                            //navController?.navigate(Rutas.PantallaHome.ruta)
                            pregunta = lista[lista.size - 1]
                        }
                    },
                    Modifier
                        .weight(1f)
                        .padding(4.dp),
                    // en modo examen no se puede ir atrás
                    enabled = !Parametros.modoExamen
                ) {
                    Icon(
                        Icons.Rounded.ArrowBack,
                        contentDescription = "Anterior"
                    )
                    Text(text = "PREV")
                }

                // siguiente
                Button(
                    onClick = {
                        //Log.i("info","Pregunta ${lista.indexOf(pregunta)} de ${lista.size}")

                        if (lista.indexOf(pregunta) != (lista.size - 1)) {
                            pregunta = lista[lista.indexOf(pregunta) + 1]
                            resultado = ""
                        } else if(Parametros.modoExamen) {
                            enviar()
                        } else {
                            pregunta = lista[0]
                        }
                    },
                    Modifier
                        .weight(1f)
                        .padding(4.dp),
                ) {
                    Text(text = "NEXT")
                    Icon(
                        Icons.Rounded.ArrowForward,
                        contentDescription = "Siguiente"
                    )
                }


            }

            Button(onClick = { enviar() },
                Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Enviar")
                Icon(Icons.Rounded.Done, contentDescription = "Enviar")
            }
        }
    }
}

fun enviar(){

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

