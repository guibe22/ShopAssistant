package com.wgg.shopassistant.ui.listas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wgg.shopassistant.data.local.entities.ListaConDetalles
import com.wgg.shopassistant.data.local.entities.ListaDetalle
import com.wgg.shopassistant.util.AlertDialogSample
import dagger.multibindings.ClassKey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaScreen(
    viewModel: listasViewModel = hiltViewModel()
) {
    val listas by viewModel.litas.collectAsState()

    Scaffold() {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (listas.isEmpty()) {
                item {
                    Text(
                        text = "Aún no has guardado nada. Comienza a registrar tus compras.",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                items(
                    items = listas.reversed(),
                    itemContent = { listaConDetalles ->
                        ListaItem(listaConDetalles,viewModel)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaItem(listaConDetalles: ListaConDetalles , viewModel: listasViewModel) {
    var detallesVisible by remember { mutableStateOf(false) }
    val openDialog = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        onClick = { openDialog.value=true }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = listaConDetalles.lista.fecha,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = { detallesVisible = !detallesVisible },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = if (detallesVisible) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = if (detallesVisible) "Ocultar detalles" else "Mostrar detalles"
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (detallesVisible) {
                listaConDetalles.detalles.forEach { detalle ->
                    DetalleList(detalle)
                }

                Spacer(modifier = Modifier.height(25.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(
                        text = "TOTAL:",
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentWidth(Alignment.Start),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text =listaConDetalles.lista.totalPrecio.toString() ,
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentWidth(Alignment.End)
                    )
                }


            }
        }
    }
    AlertDialogSample(
        OnClick= { listaConDetalles.lista.listaId?.let { viewModel.delete(it) } },
        title = "Eliminar?",
        content = "esta lista se eliminara permanentemente.",
        openDialog=openDialog
    )
}


@Composable
fun DetalleList(listaDetalle: ListaDetalle){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = listaDetalle.nombre +" X " +listaDetalle.cantidad.toString(),
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.Start),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = listaDetalle.total.toString(),
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.End)
        )
    }

}



