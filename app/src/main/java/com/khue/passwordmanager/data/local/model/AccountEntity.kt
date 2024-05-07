package com.khue.passwordmanager.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AccountEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val accountName: String,
    val userName: String,
    val password: String
)
