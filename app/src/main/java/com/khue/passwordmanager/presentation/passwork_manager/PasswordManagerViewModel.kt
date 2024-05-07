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
    val error: String? = null
)

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
            repository.getAccounts().collect { accounts ->
                _uiState.value = _uiState.value.copy(accounts = accounts, isLoading = false)
            }
        }
    }
}