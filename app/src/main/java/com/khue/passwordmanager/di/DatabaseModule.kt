package com.khue.passwordmanager.di

import android.app.Application
import androidx.room.Room
import com.khue.passwordmanager.data.local.PasswordManagerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideStockDatabase(
        app: Application
    ): PasswordManagerDatabase {
        return Room.databaseBuilder(
            app,
            PasswordManagerDatabase::class.java,
            "password_manager_db.db",
        ).build()
    }
}