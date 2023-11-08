package com.example.practicapreguntas.paginas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.practicapreguntas.Parametros
import com.example.practicapreguntas.ui.theme.PracticaPreguntasTheme

@Composable
fun PaginaHome(navController : NavHostController?){
    Column {
        Text(
            text = "QUIZ GAMER",
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
        )

        Row {
            Text(text = "Modo examen")
            Switch(checked = false, onCheckedChange = {
                Parametros.modoExamen = it
            })
        }
    }
}

@Preview
@Composable
fun HomePreview(){
    PracticaPreguntasTheme {
        PaginaHome(navController = rememberNavController())
    }
}