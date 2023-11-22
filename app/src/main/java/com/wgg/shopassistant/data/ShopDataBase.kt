package com.wgg.shopassistant.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wgg.shopassistant.data.local.entities.lista
import com.wgg.shopassistant.data.local.entities.listaDetalle
import com.wgg.shopassistant.data.local.entities.planificar
import com.wgg.shopassistant.data.local.entities.planificarDetalle

@Database(
    entities = [lista::class ,listaDetalle::class, planificar::class, planificarDetalle::class],
    version = 1,
    exportSchema = false
)
abstract class DivisionBD: RoomDatabase() {

}