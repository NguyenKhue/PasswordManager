package com.khue.passwordmanager.di

import com.khue.passwordmanager.data.local.AccountDao
import com.khue.passwordmanager.data.local.PasswordManagerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun provideUserDao(appDatabase: PasswordManagerDatabase): AccountDao {
        return appDatabase.accountDao()
    }
}