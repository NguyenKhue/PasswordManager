package com.khue.passwordmanager.data

import com.khue.passwordmanager.data.local.AccountDao
import com.khue.passwordmanager.data.mapper.toAccount
import com.khue.passwordmanager.data.mapper.toAccountEntity
import com.khue.passwordmanager.domain.model.Account
import com.khue.passwordmanager.domain.repository.PasswordManagerRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PasswordManagerRepositoryImpl @Inject constructor(
    private val accountDao: AccountDao
) : PasswordManagerRepository {
    override suspend fun insertAccount(account: Account) {
        accountDao.insertData(account.toAccountEntity())
    }

    override fun getAccounts(): Flow<ImmutableList<Account>> {
        return accountDao.getAccounts().map { accountEntities ->
            accountEntities.map {
                it.toAccount()
            }.toPersistentList()
        }
    }

    override suspend fun deleteAccount(account: Account) {
        accountDao.deleteAccount(account.id)
    }

    override suspend fun updateAccount(account: Account) {
        accountDao.updateAccount(account.toAccountEntity())
    }
}