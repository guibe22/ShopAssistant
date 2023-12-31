package com.wgg.shopassistant.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "ListaDetalle", foreignKeys = [ForeignKey(entity = Lista::class, parentColumns = ["listaId"], childColumns = ["listaId"], onDelete = CASCADE)])
class ListaDetalle(
    @PrimaryKey(autoGenerate = true)
    val listaDetalleId:Int?=null,
    var listaId:Int,
    val nombre:String,
    val precio:Double,
    val cantidad: Int,
    val total: Double
)

