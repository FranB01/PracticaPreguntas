    package com.example.practicapreguntas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.practicapreguntas.nav.ControladorNav
import com.example.practicapreguntas.paginas.ContenedorPregunta
import com.example.practicapreguntas.paginas.PaginaHome
import com.example.practicapreguntas.ui.theme.PracticaPreguntasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticaPreguntasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ControladorNav()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PracticaPreguntasTheme {
        Pregunta(nPregunta = 1,
        texto = "Es Mario un criminal de guerra?",
        idImagen = R.drawable.mario_porro,
        respuesta = true,
        navController = rememberNavController())
    }
}*/