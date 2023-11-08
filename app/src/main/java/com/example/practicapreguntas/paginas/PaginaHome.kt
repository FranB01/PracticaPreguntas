package com.example.practicapreguntas.paginas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.practicapreguntas.nav.Rutas
import com.example.practicapreguntas.ui.theme.PracticaPreguntasTheme

@Composable
fun PaginaHome(navController : NavHostController?){
    Column(
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "QUIZ GAMER",
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Modo examen")
            Switch(checked = false, onCheckedChange = {
                Parametros.modoExamen = it
            })
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Orden aleatorio")
            Switch(checked = false, onCheckedChange = {
                Parametros.modoAleatorio = it
            })
        }

        Button(
            onClick = { navController?.navigate(Rutas.ContenedorPregunta.ruta) },
            modifier = Modifier.fillMaxWidth(),
            shape = RectangleShape
            ) {
            Text(text = "Empezar")
        }
    }
}

@Preview(showSystemUi = false, showBackground = true)
@Composable
fun HomePreview(){
    PracticaPreguntasTheme {
        PaginaHome(navController = rememberNavController())
    }
}