package com.wgg.shopassistant.ui.listas

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wgg.shopassistant.data.local.entities.ListaDetalle
import com.wgg.shopassistant.util.TextBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarScreen(
    viewModel: listasViewModel = hiltViewModel()
){
    val listaDetalle by viewModel.listaDetalle.collectAsState()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Tus acciones */ }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Crear nota"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(8.dp)
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
            item {
                Registro(viewModel)
            }
                items(items = listaDetalle, itemContent = { detalle ->
                    CardDetalles(detalle = detalle)
                })
            }

        }
        }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Registro(viewModel:listasViewModel) {

    TextBox(
                    label = "Nombre",
                    value =viewModel.nombre ,
                    onValueChange = viewModel::onNombreChange,
                    isError = viewModel.nombreError,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    focusDirection = FocusDirection.Next
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextBox(
                    label = "Precio",
                    value =viewModel.precio ,
                    onValueChange = viewModel::onPrecioChange,
                    isError = viewModel.precioError,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                    focusDirection = FocusDirection.Next
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextBox(
                    label = "Cantidad",
                    value =viewModel.cantidad ,
                    onValueChange = viewModel::onCantidadChange,
                    isError = viewModel.cantidadError,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                    focusDirection = FocusDirection.Exit
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                        onClick = {
                    viewModel.agregarDetalle()
                })
    {
        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "guardar")
        Text(text = "Guardar")
    }

}

@Composable
fun CardDetalles(detalle: ListaDetalle){

Card {
    Text(text = detalle.nombre)
    Spacer(modifier = Modifier.height(16.dp))
    Text(text ="Cantidad: " +detalle.cantidad.toString())
    Spacer(modifier = Modifier.height(16.dp))
    Text(text ="Precio: " +detalle.precio.toString())
    Spacer(modifier = Modifier.height(16.dp))
    Text(text ="Total: " +detalle.total.toString())
}
}



@Preview
@Composable
fun AgregarScreenPreview(){
    AgregarScreen()
}
