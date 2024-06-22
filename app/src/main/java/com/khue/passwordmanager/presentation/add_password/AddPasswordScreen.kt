package com.khue.passwordmanager.presentation.add_password

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.khue.passwordmanager.R
import com.khue.passwordmanager.ui.theme.PasswordManagerTheme

@Composable
fun AddPasswordScreen(
    modifier: Modifier = Modifier,
    onAddAccount: (String, String, String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        var accountName by rememberSaveable {
            mutableStateOf("")
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = accountName,
            onValueChange = {
                accountName = it
            },
            placeholder = {
                Text(
                    text = "Account Name",
                    style = MaterialTheme.typography.titleMedium
                )
            },
            textStyle = MaterialTheme.typography.titleMedium,
            keyboardOptions =  KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            )
        )

        var accountUserNameEmail by rememberSaveable {
            mutableStateOf("")
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = accountUserNameEmail,
            onValueChange = {
                accountUserNameEmail = it
            },
            placeholder = {
                Text(
                    text = "Username/Email",
                    style = MaterialTheme.typography.titleMedium
                )
            },
            textStyle = MaterialTheme.typography.titleMedium,
            keyboardOptions =  KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            )
        )

        var password by rememberSaveable {
            mutableStateOf("")
        }
        var isShowPassword by rememberSaveable {
            mutableStateOf(false)
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = {
                password = it
            },
            placeholder = {
                Text(
                    text = "Password",
                    style = MaterialTheme.typography.titleMedium
                )
            },
            textStyle = MaterialTheme.typography.titleMedium,
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
            keyboardOptions =  KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
        )

        TextButton(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                onAddAccount(accountName, accountUserNameEmail, password)
            }
        ) {
            Text(
                text = "Add New Account",
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@PreviewLightDark
@Composable
private fun AddPasswordScreenPrev() {
    PasswordManagerTheme {
        AddPasswordScreen() { _, _, _ -> }
    }
}