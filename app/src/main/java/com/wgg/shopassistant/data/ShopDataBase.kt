package com.wgg.shopassistant.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wgg.shopassistant.data.local.dao.ListaDao
import com.wgg.shopassistant.data.local.entities.Lista
import com.wgg.shopassistant.data.local.entities.ListaDetalle

@Database(
    entities = [Lista::class ,ListaDetalle::class],
    version = 2,
    exportSchema = false
)
abstract class ShopDataBase: RoomDatabase() {
    abstract fun ListaDao(): ListaDao
}