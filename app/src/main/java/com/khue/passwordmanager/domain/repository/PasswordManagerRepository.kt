package com.khue.passwordmanager.domain.repository

import com.khue.passwordmanager.domain.model.Account
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow

interface PasswordManagerRepository {
    suspend fun insertAccount(account: Account)

    fun getAccounts(): Flow<ImmutableList<Account>>

    suspend fun deleteAccount(account: Account)

    suspend fun updateAccount(account: Account)
}