package com.wgg.shopassistant.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "planificarDetalle", foreignKeys = [ForeignKey(entity = lista::class, parentColumns = ["planificarId"], childColumns = ["planificarId"], onDelete = ForeignKey.CASCADE)])
class planificarDetalle(
    @PrimaryKey(autoGenerate = true)
    val planificarDetalleId :Int?=null,
    val planificarId:Int,
    val nombre: String,
    val cantidad: Int,
)