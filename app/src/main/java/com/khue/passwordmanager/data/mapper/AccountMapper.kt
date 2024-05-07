package com.khue.passwordmanager.data.mapper

import com.khue.passwordmanager.data.local.model.AccountEntity
import com.khue.passwordmanager.domain.model.Account

fun Account.toAccountEntity() = AccountEntity(
    id = id,
    accountName = accountName,
    userName = userName,
    password = password
)

fun AccountEntity.toAccount() = Account(
    id = id,
    accountName = accountName,
    userName = userName,
    password = password
)