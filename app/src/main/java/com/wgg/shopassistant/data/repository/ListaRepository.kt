package com.wgg.shopassistant.data.repository

import com.wgg.shopassistant.data.local.dao.ListaDao
import com.wgg.shopassistant.data.local.entities.Lista
import com.wgg.shopassistant.data.local.entities.ListaDetalle
import javax.inject.Inject

class ListaRepository@Inject constructor(
    private val Dao:ListaDao
) {
suspend fun saveLista(Lista:Lista,detalles: List<ListaDetalle>) = Dao.saveListaWithDetalles(Lista,detalles)
suspend fun  deleteLista(ListaId:Int) = Dao.deleteListaWithDetalles(ListaId)
fun  getListas() = Dao.getAllListasConDetalles()
}