package com.example.torneoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.torneoapp.ui.theme.TorneoappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TorneoappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    InicioScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun InicioScreen(modifier: Modifier = Modifier) {
    val participantes = remember {
        mutableStateListOf<Participante>()
    }


    var nombre by rememberSaveable() { mutableStateOf("")}
    var alias by rememberSaveable() { mutableStateOf("")}

    val formularioValido = nombre.isNotBlank() && alias.isNotBlank()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(20.dp)
    ){

        Text(text = "Torneo APP")

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = {nombre = it},
            label = { Text("Nombre") },
            placeholder = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = alias,
            onValueChange = {alias = it},
            label = { Text("Alias") },
            placeholder = { Text("Alias") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            enabled = formularioValido,
            onClick = {
            participantes.add(Participante(nombre,alias))
        }) { Text("Añade al participante")}

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "LISTA DE PARTICIPANTES")

        LazyColumn{
            items(participantes){  participante ->
                TarjetaParticipante(participante, onEliminarClick = {
                    participantes.remove(participante)
                })
                Spacer (modifier = Modifier.height(20.dp))
            }
        }




}
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TorneoappTheme {
        InicioScreen(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White))
    }
}

data class Participante(
    val nombre: String,
    val alias: String
)

@Composable
fun TarjetaParticipante(
    participante: Participante,
    onEliminarClick: () -> Unit)
{
    Card(modifier = Modifier.fillMaxWidth() ){
        Column(modifier = Modifier.padding(12.dp)){
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Column(){
                    Text (text = participante.nombre)
                    Text (text = "Alias: ${participante.alias}")}

                Spacer(modifier = Modifier.weight(1f))
                Button (onClick = {onEliminarClick()
                    }){
                    Text("Eliminar jugador")
                }
            }


        }

    }
}


