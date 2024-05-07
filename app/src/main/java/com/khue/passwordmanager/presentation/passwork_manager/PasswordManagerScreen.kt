package com.khue.passwordmanager.presentation.passwork_manager

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.khue.passwordmanager.domain.model.Account
import com.khue.passwordmanager.ui.theme.PasswordManagerTheme
import com.khue.passwordmanager.ui.theme.appColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordManagerScreen(
    modifier: Modifier = Modifier,
    uiState: PasswordManagerUiState = PasswordManagerUiState(),
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Password Manager",
                    color = MaterialTheme.colorScheme.appColor
                )
            })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
        ) {

        }
    }
}

@Composable
fun AccountItem(
    modifier: Modifier = Modifier,
    account: Account,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        onClick = onClick
    ) {
        
    }
}

@PreviewLightDark
@Composable
private fun AccountItemPrev() {
    PasswordManagerTheme {
        AccountItem(
            account = Account(
                id = 1,
                accountName = "username",
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
        PasswordManagerScreen()
    }
}