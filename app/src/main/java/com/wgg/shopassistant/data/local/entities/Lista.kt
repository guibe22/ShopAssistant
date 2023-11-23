package com.wgg.shopassistant.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "listas")
data class Lista (
    @PrimaryKey(autoGenerate = true)
    val listaId: Int? =null,
    val fecha: String,
    val totalPrecio:Double,
    val totalProductos:Int
)