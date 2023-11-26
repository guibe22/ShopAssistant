package com.wgg.shopassistant.ui.listas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wgg.shopassistant.data.local.entities.ListaDetalle
import com.wgg.shopassistant.util.TextBox
import  androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import com.wgg.shopassistant.data.local.entities.Lista
import com.wgg.shopassistant.util.AlertDialogSample

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarScreen(
    viewModel: listasViewModel = hiltViewModel()
){
    val listaDetalle by viewModel.listaDetalle.collectAsState()


    val mostrarRegistro = remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                mostrarRegistro.value = !mostrarRegistro.value
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(it),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    listaView(viewModel)
                }
                item{
                    if (mostrarRegistro.value) {
                        Registro(viewModel,  mostrarRegistro)
                    } else {
                        if(listaDetalle.isNotEmpty()){
                            val openDialog = remember { mutableStateOf(false) }
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .background(color = Color(0xFF4CAF50), shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            IconButton(
                                onClick = {
                                    openDialog.value= !openDialog.value
                                },
                                modifier = Modifier
                                    .padding(8.dp)
                                    .size(56.dp)
                                    .align(Alignment.Center)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color.White, // White color for the icon
                                    modifier = Modifier.size(32.dp) // Adjust the size of the icon as needed
                                )
                            }
                        }
                            AlertDialogSample(
                                OnClick= { viewModel.save() },
                                title = "Guardar Lista?",
                                content = "",
                                openDialog=openDialog
                            )
                    }else{
                            Text(
                                text = "Comienza a agregar productos!",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                textAlign = TextAlign.Center
                            )
                    }
                    }
                }
                items(
                    items = listaDetalle.reversed(),
                    itemContent = { detalle ->
                        CardDetalles(detalle = detalle, viewModel)
                    }
                )
            }
        }
    }



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Registro(viewModel: listasViewModel,  mostrarRegistro: MutableState<Boolean>) {
    val fadeInAlpha = remember { Animatable(0f) }


    LaunchedEffect(key1 = true) {
        fadeInAlpha.animateTo(1f, animationSpec = tween(durationMillis = 500))
    }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .graphicsLayer(alpha = fadeInAlpha.value)
    ) {
        TextBox(
            label = "Nombre",
            value = viewModel.nombre,
            onValueChange = viewModel::onNombreChange,
            isError = viewModel.nombreError,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
            focusDirection = FocusDirection.Next
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextBox(
            label = "Precio",
            value = viewModel.precio,
            onValueChange = viewModel::onPrecioChange,
            isError = viewModel.precioError,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next,
            focusDirection = FocusDirection.Next
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextBox(
            label = "Cantidad",
            value = viewModel.cantidad,
            onValueChange = viewModel::onCantidadChange,
            isError = viewModel.cantidadError,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
            focusDirection = FocusDirection.Exit ,
            onDoneAction= {
                viewModel.agregarDetalle()
                mostrarRegistro.value = !mostrarRegistro.value
            }
        )


    }
}

@Composable
fun listaView(viewModel: listasViewModel){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = "Total: " +viewModel.totalPrecio,
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.Start),
            fontWeight = FontWeight.Bold
        )
        Text(
            text =  "Cantidad: " +viewModel.totalProductos,
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.End),
            fontWeight = FontWeight.Bold
        )
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardDetalles(detalle: ListaDetalle, viewModel: listasViewModel) {
    val openDialog = remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            onClick = { openDialog.value=true },
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = detalle.nombre,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            DetalleItem("Cantidad", detalle.cantidad.toString())
            DetalleItem("Precio", detalle.precio.toString())
            DetalleItem("Total", detalle.total.toString())
        }
    }
    AlertDialogSample(
        OnClick= { viewModel.eliminarDetalle(detalle) },
        title = "Eliminar?",
        content = "este producto se eliminara del listado",
        openDialog=openDialog
    )
}
@Composable
fun DetalleItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = "$label:",
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.Start),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value,
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.End)
        )
    }


}



