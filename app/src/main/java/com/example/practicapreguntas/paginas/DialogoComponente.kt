package com.example.practicapreguntas.paginas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.practicapreguntas.data_classes.Dialogo

@Composable
fun DialogoComponente(
    salir : () -> Unit,
    dialogo: Dialogo,
) {
    Dialog(onDismissRequest = { salir }) {
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
                    text = dialogo.texto,
                    modifier = Modifier.padding(15.dp),
                    textAlign = TextAlign.Center
                )
                TextButton(
                    onClick = { salir },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "¿Reintentar?")
                }
            }
        }
    }
}