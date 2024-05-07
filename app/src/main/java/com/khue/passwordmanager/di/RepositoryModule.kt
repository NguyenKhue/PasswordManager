package com.khue.passwordmanager.di

import com.khue.passwordmanager.data.PasswordManagerRepositoryImpl
import com.khue.passwordmanager.domain.repository.PasswordManagerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPasswordManagerRepository(
        passwordManagerRepository: PasswordManagerRepositoryImpl
    ): PasswordManagerRepository
}