package com.khue.passwordmanager.presentation.passwork_manager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khue.passwordmanager.domain.model.Account
import com.khue.passwordmanager.domain.repository.PasswordManagerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


data class PasswordManagerUiState(
    val isLoading: Boolean = false,
    val accounts: ImmutableList<Account> = persistentListOf(),
    val error: String? = null,
    val isShowAccountDetail: Boolean = false,
    val isAddAccount: Boolean = false,
    val selectedAccount: Account? = null
)

sealed interface PasswordManagerIntent {
    data class ShowAddAccount(val isShow: Boolean): PasswordManagerIntent
    data class ShowAccountDetail(val isShow: Boolean): PasswordManagerIntent
    data class UpdateAccount(val account: Account): PasswordManagerIntent
    data class DeleteAccount(val account: Account): PasswordManagerIntent
    data class InsertAccount(val account: Account): PasswordManagerIntent
    data class OnSelectAccount(val account: Account): PasswordManagerIntent
}

@HiltViewModel
class PasswordManagerViewModel @Inject constructor(
    private val repository: PasswordManagerRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(PasswordManagerUiState())
    val uiState = _uiState.onStart {
        getAccounts()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PasswordManagerUiState())

    private fun getAccounts() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = _uiState.value.copy(isLoading = true)
            repository.getAccounts()
                .collect { accounts ->
                _uiState.value = _uiState.value.copy(accounts = accounts, isLoading = false)
            }
        }
    }

    fun onIntent(intent: PasswordManagerIntent) {
        when (intent) {
            is PasswordManagerIntent.ShowAddAccount -> {
                _uiState.value = _uiState.value.copy(isAddAccount = intent.isShow)
            }
            is PasswordManagerIntent.ShowAccountDetail -> {
                _uiState.value = _uiState.value.copy(isShowAccountDetail = intent.isShow)
            }
            is PasswordManagerIntent.UpdateAccount -> {
                updateAccount(intent.account)
            }
            is PasswordManagerIntent.DeleteAccount -> {
                deleteAccount(intent.account)
            }
            is PasswordManagerIntent.InsertAccount -> {
                insertAccount(intent.account)
            }
            is PasswordManagerIntent.OnSelectAccount -> {
                _uiState.value = _uiState.value.copy(selectedAccount = intent.account)
            }
        }
    }

    private fun insertAccount(account: Account) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertAccount(account = account)
        }
    }

    private fun deleteAccount(account: Account) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAccount(account = account)
        }
    }

    private fun updateAccount(account: Account) {
        viewModelScope.launch {
            repository.updateAccount(account = account)
        }
    }
}