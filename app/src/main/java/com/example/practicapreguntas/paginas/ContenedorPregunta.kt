package com.example.practicapreguntas.paginas

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.practicapreguntas.Parametros
import com.example.practicapreguntas.R
import com.example.practicapreguntas.data_classes.Dialogo
import com.example.practicapreguntas.listas.ListaDialogosFin
import com.example.practicapreguntas.listas.ListaPreguntas
import com.example.practicapreguntas.nav.Rutas
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

    var lista = rememberSaveable { ListaPreguntas.cargarPreguntas() }
    var pregunta by rememberSaveable { mutableStateOf(lista[0]) }
    var resultado by rememberSaveable { mutableStateOf("") }

    var colorTextoResultado by rememberSaveable{
        mutableStateOf(Blanco)
    }
    var colorBotonTrue by rememberSaveable{
        mutableStateOf(Gris)
    }
    var colorBotonFalse by rememberSaveable{
        mutableStateOf(Gris)
    }

    var dialogoVisible by rememberSaveable{ mutableStateOf(false) }
    var dialogo by rememberSaveable{
        mutableStateOf(
            Dialogo("Si ves esto algo va mal", R.drawable.mario_porro)
        )
    }

    fun boolAString(b: Boolean): String {
        if (b) return "verdadero"
        else return "falso"
    }

    fun cargarColor() {
        if (pregunta.haRespondido) {
            // colores botones
            // si verde, no rojo
            if (pregunta.respuesta) {
                colorBotonTrue = Verde
                colorBotonFalse = Rojo
            } else {
                colorBotonTrue = Rojo
                colorBotonFalse = Verde
            }
        } else {
            colorBotonTrue = Gris
            colorBotonFalse = Gris
        }
    }

    fun checkResultado(respuestaUsuario: Boolean) {
        if (!pregunta.haRespondido) {
            if (pregunta.respuesta == respuestaUsuario) {
                resultado = "Respuesta correcta :D"
                colorTextoResultado = Verde
                Parametros.correctas++
            } else {
                resultado = "MAAAL!!!!! pusiste ${boolAString(respuestaUsuario)} " +
                        "pero era ${boolAString(!respuestaUsuario)}!!!!!"
                colorTextoResultado = Rojo
            }
        }
        pregunta.haRespondido = true
        cargarColor()
    }

    fun enviar() {
        Log.i("info", "${Parametros.correctas} preguntas correctas")
        when (Parametros.correctas) {
            0, 1, 2 -> dialogo = ListaDialogosFin.dialogo012
            3, 4 -> dialogo = ListaDialogosFin.dialogo34
            5 -> dialogo = ListaDialogosFin.dialogo5
        }
        dialogoVisible = true
    }

    fun salir(){
        dialogoVisible = false
        navController?.navigate(Rutas.PantallaHome.ruta)
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
                            pregunta = lista[lista.size - 1]
                        }
                        cargarColor()
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
                        } else if (Parametros.modoExamen) {
                            enviar()
                        } else {
                            pregunta = lista[0]
                        }
                        cargarColor()
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

            Button(
                onClick = { enviar() },
                Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Enviar")
                Icon(Icons.Rounded.Done, contentDescription = "Enviar")
            }
        }
    }

    if (dialogoVisible){
        /*
        DialogoComponente(salir = {
            dialogoVisible = false
            navController?.navigate(Rutas.PantallaHome.ruta)
            //salir()
        },
            dialogo = dialogo,
        )
         */
        Dialog(onDismissRequest = { salir() }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(15.dp),
                shape = RoundedCornerShape(15.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = dialogo.idImagen),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.height(160.dp)
                    )
                    Text(
                        "Nota final\n${Parametros.correctas}/5",
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp
                    )
                    Text(
                        text = dialogo.texto,
                        modifier = Modifier.padding(15.dp),
                        textAlign = TextAlign.Center
                    )
                    TextButton(
                        onClick = { salir() },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "¿Reintentar?")
                    }
                }
            }
        }
    }


}


@Composable
//@Preview(showBackground = true)
fun previewPregunta() {
    PracticaPreguntasTheme {
        ContenedorPregunta(
            navController = rememberNavController()
        )
    }
}

