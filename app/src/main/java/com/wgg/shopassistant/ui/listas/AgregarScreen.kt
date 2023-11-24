package com.wgg.shopassistant.ui.listas

import androidx.compose.animation.Animatable
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wgg.shopassistant.data.local.entities.ListaDetalle
import com.wgg.shopassistant.util.TextBox
import  androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.graphicsLayer

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
                items(
                    items = listaDetalle.reversed(),
                    itemContent = { detalle ->
                        CardDetalles(detalle = detalle)
                    }
                )
            }

        }
        }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Registro(viewModel: listasViewModel) {
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
            onDoneAction= { viewModel.agregarDetalle() }
        )


    }
}


@Composable
fun CardDetalles(detalle: ListaDetalle) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
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
}
@Composable
fun DetalleItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = label + ":",
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


