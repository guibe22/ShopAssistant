package com.wgg.shopassistant.data.local.dao

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import androidx.room.Insert
import androidx.room.Query
import com.wgg.shopassistant.data.local.entities.Lista
import com.wgg.shopassistant.data.local.entities.ListaConDetalles
import com.wgg.shopassistant.data.local.entities.ListaDetalle
import kotlinx.coroutines.flow.Flow

@Dao
interface ListaDao {

    @Transaction
    suspend fun saveListaWithDetalles(lista: Lista, detalles: List<ListaDetalle>) {
        val listaId = saveLista(lista)
        detalles.forEach { it.listaId = listaId.toInt() }
        saveDetallesListas(detalles)
    }

    @Query("SELECT * FROM listas")
    fun getAllListas(): Flow<List<Lista>>

    @Query("SELECT * FROM listaDetalle WHERE listaId = :listaId")
    fun getDetallesByLista(listaId: Int): Flow<List<ListaDetalle>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
      suspend fun saveLista(lista: Lista): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDetallesListas(detalles: List<ListaDetalle>)

    @Transaction
    suspend fun deleteListaWithDetalles(listaId: Int) {
        deleteDetallesByListaId(listaId)
        deleteListaById(listaId)
    }

    @Query("DELETE FROM listas WHERE listaId = :listaId")
    suspend fun deleteListaById(listaId: Int)

    @Query("DELETE FROM listaDetalle WHERE listaId = :listaId")
    suspend fun deleteDetallesByListaId(listaId: Int)

    @Transaction
    @Query("SELECT * FROM listas")
    fun getAllListasConDetalles(): Flow<List<ListaConDetalles>>

}