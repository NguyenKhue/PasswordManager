package com.khue.passwordmanager.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.khue.passwordmanager.data.local.model.AccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(accountDao: AccountEntity)

    @Query("SELECT * FROM accountentity")
    fun getAccounts(): Flow<List<AccountEntity>>

    @Query("DELETE FROM accountentity Where id = :accountId")
    suspend fun deleteAccount(accountId: Int)

    @Update
    suspend fun updateAccount(
        accountEntity: AccountEntity
    )
}