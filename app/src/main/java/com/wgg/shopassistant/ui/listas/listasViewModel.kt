package com.wgg.shopassistant.ui.listas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wgg.shopassistant.data.local.entities.Lista
import com.wgg.shopassistant.data.local.entities.ListaConDetalles
import com.wgg.shopassistant.data.local.entities.ListaDetalle
import com.wgg.shopassistant.data.repository.ListaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject
@HiltViewModel
class listasViewModel@Inject constructor(
    private val repository: ListaRepository
) : ViewModel() {

    var totalPrecio by mutableStateOf("0")
    var totalProductos by mutableStateOf("0")

    var fechaError  by mutableStateOf(false)
    var totalPrecioError by mutableStateOf(false)
    var totalProductosError by mutableStateOf(false)

    var nombre by mutableStateOf("")
    var precio by mutableStateOf("")
    var cantidad by mutableStateOf("")
    var total by mutableStateOf("")

    var nombreError by mutableStateOf(false)
    var precioError by mutableStateOf(false)
    var cantidadError by mutableStateOf(false)
    var totalError by mutableStateOf(false)

    fun onNombreChange(valor:String){
         nombre=valor
        nombreError= nombre.isNullOrBlank()

    }
    fun onCantidadChange(valor:String){
        cantidad=valor
        cantidadError= cantidad.isNullOrBlank()

    }
    fun onPrecioChange(valor:String){
        precio=valor
        precioError= precio.isNullOrBlank()
    }

    private val _listaDetalle = MutableStateFlow(mutableListOf<ListaDetalle>())
    val listaDetalle = _listaDetalle.asStateFlow()

    val litas: StateFlow<List<ListaConDetalles>> = repository.getListas()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun save(){
        if(listaDetalle.value.isNullOrEmpty()){
            return
        }
        viewModelScope.launch {
            val lista = Lista(
                fecha= LocalDate.now().toString(),
                totalProductos = totalProductos.toInt(),
                totalPrecio = totalPrecio.toDouble()
            )
            repository.saveLista(lista, listaDetalle.value)
            totalProductos=""
            totalPrecio=""
            _listaDetalle.value = emptyList<ListaDetalle>().toMutableList()

        }
    }


    fun agregarDetalle() {
        if(ValidarDetalle()){
            return
        }

        viewModelScope.launch {

            var totalDetalle by mutableStateOf(0.0)
            totalDetalle = (precio.toDouble() * cantidad.toInt())
            val nuevoDetalle = ListaDetalle(
                nombre =nombre,
                precio =precio.toDouble(),
                cantidad = cantidad.toInt(),
                total =totalDetalle,
                listaId = 0
                )
            _listaDetalle.value = _listaDetalle.value.toMutableList().apply {
                add(nuevoDetalle)
            }
            var totalPrecioInt by mutableStateOf(0.0)
            var totalProductosInt by mutableStateOf(0)

            totalPrecioInt = (_listaDetalle.value.sumOf { it.total } ?: 0.0)
            totalProductosInt = _listaDetalle.value.sumOf { it.cantidad }
            totalPrecio= totalPrecioInt.toString()
            totalProductos=  totalProductosInt.toString()
            LimpiarDetalle()


        }

    }
    fun eliminarDetalle(detalle: ListaDetalle) {
        viewModelScope.launch {
            _listaDetalle.value = _listaDetalle.value.toMutableList().apply {
                remove(detalle)
            }
            var totalPrecioInt by mutableStateOf(0.0)
            var totalProductosInt by mutableStateOf(0)

            totalPrecioInt = (_listaDetalle.value.sumOf { it.total } ?: 0.0)
            totalProductosInt = _listaDetalle.value.sumOf { it.cantidad }
            totalPrecio= totalPrecioInt.toString()
            totalProductos=  totalProductosInt.toString()

        }
    }

    fun LimpiarDetalle() {
        nombre = ""
        precio = ""
        cantidad = ""
        total = ""
        nombreError =false
        precioError =false
        cantidadError =false
        totalError =false
    }
    fun ValidarDetalle():Boolean {
        onNombreChange(nombre)
        onPrecioChange(precio)
        onCantidadChange(cantidad)

        return nombreError || precioError || cantidadError
    }


}