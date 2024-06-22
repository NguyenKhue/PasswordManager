package com.khue.passwordmanager.presentation.account_detail

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.khue.passwordmanager.R
import com.khue.passwordmanager.domain.model.Account
import com.khue.passwordmanager.ui.theme.PasswordManagerTheme
import com.khue.passwordmanager.ui.theme.appColor
import com.khue.passwordmanager.ui.theme.red
import com.khue.passwordmanager.ui.theme.topHeadingColor
import com.khue.util.decrypt
import com.khue.util.generateAESKey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountDetailScreen(
    modifier: Modifier = Modifier,
    account: Account,
    onEditAccount: (Account) -> Unit,
    onDeleteAccount: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Account Details",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.appColor
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Account Type",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.topHeadingColor
        )

        Spacer(modifier = Modifier.height(8.dp))

        var accountName by rememberSaveable {
            mutableStateOf(account.accountName)
        }

        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            value = accountName,
            onValueChange = {
                accountName = it
            },
            textStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.secondary
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            decorationBox = { innerTextField ->
                TextFieldDefaults.DecorationBox(
                    value = accountName,
                    innerTextField = innerTextField,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    enabled = true,
                    contentPadding = PaddingValues(0.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = MaterialTheme.colorScheme.secondary,
                        unfocusedTextColor = MaterialTheme.colorScheme.secondary,
                    )
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Username/Email",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.topHeadingColor
        )

        Spacer(modifier = Modifier.height(8.dp))

        var accountUserNameEmail by rememberSaveable {
            mutableStateOf(account.userName)
        }

        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            value = accountUserNameEmail,
            onValueChange = {
                accountUserNameEmail = it
            },
            textStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.secondary
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            decorationBox = { innerTextField ->
                TextFieldDefaults.DecorationBox(
                    value = accountUserNameEmail,
                    innerTextField = innerTextField,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    enabled = true,
                    contentPadding = PaddingValues(0.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = MaterialTheme.colorScheme.secondary,
                        unfocusedTextColor = MaterialTheme.colorScheme.secondary,
                    )
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Password",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.topHeadingColor
        )

        Spacer(modifier = Modifier.height(8.dp))

        var password by rememberSaveable {
            val key = generateAESKey(accountName = account.accountName)
            val decryptPassword = decrypt(account.password, key = key) ?: ""
            mutableStateOf(decryptPassword)
        }
        var isShowPassword by rememberSaveable {
            mutableStateOf(false)
        }

        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = {
                password = it
            },
            textStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.secondary
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (isShowPassword) VisualTransformation.None else PasswordVisualTransformation(),
            decorationBox = { innerTextField ->
                TextFieldDefaults.DecorationBox(
                    value = password,
                    innerTextField = innerTextField,
                    singleLine = true,
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    enabled = true,
                    contentPadding = PaddingValues(0.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = MaterialTheme.colorScheme.secondary,
                        unfocusedTextColor = MaterialTheme.colorScheme.secondary,
                    ),
                    trailingIcon = {
                        IconButton(onClick = { isShowPassword = !isShowPassword }) {
                            Icon(
                                painter = if (isShowPassword)
                                    painterResource(id = R.drawable.eye_svgrepo_com)
                                else painterResource(id = R.drawable.eye_off_svgrepo_com),
                                contentDescription = ""
                            )
                        }
                    },
                    visualTransformation = if (isShowPassword) VisualTransformation.None else PasswordVisualTransformation(),
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            TextButton(
                modifier = Modifier
                    .weight(1f),
                onClick = {
                    onEditAccount(account.copy(accountName = accountName, userName = accountUserNameEmail, password = password))
                }
            ) {
                Text(
                    text = "Edit",
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            TextButton(
                modifier = Modifier
                    .weight(1f),
                onClick = onDeleteAccount,
                colors = ButtonDefaults.textButtonColors(
                    containerColor = MaterialTheme.colorScheme.red
                )
            ) {
                Text(
                    text = "Delete",
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun AccountDetailScreenPrev() {
    PasswordManagerTheme {
        AccountDetailScreen(
            account = Account(
                accountName = "ajshdgjasdg",
                userName = "kasdhakhadka",
                password = "kasdhakhadka"
            ),
            onEditAccount = { _ -> },
            onDeleteAccount = {}
        )
    }
}