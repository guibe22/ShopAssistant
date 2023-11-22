package com.wgg.shopassistant.data.local.dao

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import androidx.room.Insert
import androidx.room.Query
import com.wgg.shopassistant.data.local.entities.lista
import com.wgg.shopassistant.data.local.entities.listaDetalle
import kotlinx.coroutines.flow.Flow

@Dao
interface  listaDao {
    @Transaction
    suspend fun saveListaWithDetalles(lista: lista, detalles: List<listaDetalle>) {
        val listaId = saveLista(lista)

        detalles.forEach { it.listaId = listaId }

        saveDetallesListas(detalles)
    }
    @Query("SELECT * FROM listas")
    fun getAllDivisions(): Flow<List<lista>>

    @Query("SELECT * FROM listaDetalle WHERE listaId = :listaId")
    fun getDetallesByLista(listaId: Int): Flow<List<listaDetalle>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLista(lista: lista): Int
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDetallesListas(detalles: List<listaDetalle>)

}