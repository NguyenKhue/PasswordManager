package com.khue.passwordmanager.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.khue.passwordmanager.data.local.model.AccountEntity

@Database(entities = [AccountEntity::class], version = 1, exportSchema = true)
abstract class PasswordManagerDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
}