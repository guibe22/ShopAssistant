package com.wgg.shopassistant.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planificaciones")
data class planificar(
    @PrimaryKey(autoGenerate = true)
    val planificarId: Int?=null,
    val fecha: String,
    val totalProductos:Int
)