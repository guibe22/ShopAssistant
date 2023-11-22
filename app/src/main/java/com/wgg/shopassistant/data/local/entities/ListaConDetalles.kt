package com.wgg.shopassistant.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ListaConDetalles(
    @Embedded val lista: lista,
    @Relation(
        parentColumn = "listaId",
        entityColumn = "listaId"
    )
    val detalles: List<listaDetalle>
)