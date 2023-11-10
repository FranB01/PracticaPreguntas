package com.example.practicapreguntas.paginas

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.practicapreguntas.Parametros
import com.example.practicapreguntas.listas.ListaPreguntas
import com.example.practicapreguntas.nav.Rutas
import com.example.practicapreguntas.ui.theme.PracticaPreguntasTheme

@Composable
fun PaginaHome(navController: NavHostController?) {

    var modoExamen by remember { mutableStateOf(Parametros.modoExamen) }
    var modoAleatorio by remember { mutableStateOf(Parametros.modoAleatorio) }

    Column(
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "QUIZ GAMER",
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
        )

        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Modo examen")
                Switch(checked = modoExamen, onCheckedChange = {
                    modoExamen = it
                    Parametros.modoExamen = it
                })
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Orden aleatorio")
                Switch(checked = modoAleatorio, onCheckedChange = {
                    modoAleatorio = it
                    Parametros.modoAleatorio = it
                })
            }
        }

        Button(
            onClick = {
                Log.i("info","Empezando partida. Modo examen: ${Parametros.modoExamen}.\n" +
                        "Modo random: ${Parametros.modoAleatorio}")
                reset()
                navController?.navigate(Rutas.ContenedorPregunta.ruta)
                      },
            modifier = Modifier.fillMaxWidth(),
            shape = RectangleShape
        ) {
            Text(text = "Empezar")
        }
    }
}

// Resetea valores a los predeterminados
fun reset(){
    Parametros.correctas = 0
    ListaPreguntas.shuffled = false
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomePreview() {
    PracticaPreguntasTheme {
        PaginaHome(navController = rememberNavController())
    }
}