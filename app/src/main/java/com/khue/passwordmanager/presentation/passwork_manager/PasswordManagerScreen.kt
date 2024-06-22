package com.khue.passwordmanager.presentation.passwork_manager

import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import com.khue.passwordmanager.R
import com.khue.passwordmanager.domain.model.Account
import com.khue.passwordmanager.presentation.account_detail.AccountDetailScreen
import com.khue.passwordmanager.presentation.add_password.AddPasswordScreen
import com.khue.passwordmanager.ui.theme.PasswordManagerTheme
import com.khue.passwordmanager.ui.theme.appColor
import com.khue.util.BiometricHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordManagerScreen(
    modifier: Modifier = Modifier,
    uiState: PasswordManagerUiState = PasswordManagerUiState(),
    onIntent: (PasswordManagerIntent) -> Unit,
) {
    val context = LocalContext.current as FragmentActivity

    val biometricPrompt: BiometricPrompt = remember {
        BiometricHelper.getBiometricPrompt(
            context,
            onAuthSucceed = {
                onIntent(PasswordManagerIntent.ShowAccountDetail(true))
            }
        )
    }

    val sheetState = rememberModalBottomSheetState()

    if (uiState.isAddAccount) {
        // issue: https://issuetracker.google.com/issues/290893168
        // issue: https://issuetracker.google.com/issues/325102884
        ModalBottomSheet(
            onDismissRequest = {
                onIntent(PasswordManagerIntent.ShowAddAccount(false))
            },
            sheetState = sheetState
        ) {
            AddPasswordScreen { accountName, userName, password ->
                onIntent(
                    PasswordManagerIntent.InsertAccount(
                        Account(
                            accountName = accountName,
                            userName = userName,
                            password = password
                        )
                    )
                )
            }
        }
    }

    if (uiState.isShowAccountDetail) {
        ModalBottomSheet(
            onDismissRequest = {
                onIntent(PasswordManagerIntent.ShowAccountDetail(false))
            },
            sheetState = sheetState
        ) {
            uiState.selectedAccount?.let {
                AccountDetailScreen(
                    account = it,
                    onEditAccount = { account ->
                        onIntent(PasswordManagerIntent.UpdateAccount(account))
                    },
                    onDeleteAccount = {
                        onIntent(PasswordManagerIntent.DeleteAccount(it))
                    }
                )
            }
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Password Manager",
                    color = MaterialTheme.colorScheme.appColor
                )
            })
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.size(55.dp),
                onClick = {
                    onIntent(PasswordManagerIntent.ShowAddAccount(true))
                },
                shape = RoundedCornerShape(10.dp),
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = ""
                )
            }
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            contentPadding = PaddingValues(vertical = 5.dp)
        ) {
            items(uiState.accounts, key = { it.id }) { account ->
                AccountItem(
                    account = account,
                    onClick = {
                        if (BiometricHelper.isBiometricAvailable(context)) {
                            onIntent(PasswordManagerIntent.OnSelectAccount(account))
                            biometricPrompt.authenticate(
                                BiometricHelper.getPromptInfo(context)
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun AccountItem(
    modifier: Modifier = Modifier,
    account: Account,
    onClick: () -> Unit,
) {
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 1.dp,
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = account.accountName,
                style = MaterialTheme.typography.titleMedium
            )

            BasicTextField(
                modifier = Modifier.weight(1f),
                value = account.password,
                onValueChange = {},
                readOnly = true,
                enabled = false,
                textStyle = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                visualTransformation = PasswordVisualTransformation()
            )

            Icon(
                painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                contentDescription = ""
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun AccountItemPrev() {
    PasswordManagerTheme {
        AccountItem(
            account = Account(
                id = 1,
                accountName = "Username",
                userName = "username",
                password = "password",

                ),
            onClick = {}
        )
    }
}

@PreviewLightDark
@Composable
private fun PasswordManagerScreenPrev() {
    PasswordManagerTheme {
        PasswordManagerScreen(
            onIntent = {}
        )
    }
}