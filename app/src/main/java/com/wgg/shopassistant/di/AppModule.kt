package com.wgg.shopassistant.di

import android.content.Context
import androidx.room.Room
import com.wgg.shopassistant.data.ShopDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn( SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providesPrestamoDatabase(@ApplicationContext appContext: Context): ShopDataBase =
        Room.databaseBuilder(
            appContext,
            ShopDataBase::class.java,
            "ShopDB.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providesListaDao(db: ShopDataBase) = db.ListaDao()


}
