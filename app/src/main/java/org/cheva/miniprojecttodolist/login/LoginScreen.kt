package org.cheva.miniprojecttodolist.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.cheva.miniprojecttodolist.navigation.DashboardScreen
import org.cheva.miniprojecttodolist.navigation.RegisterScreen
import org.cheva.miniprojecttodolist.ui.components.ResultDialog
import org.cheva.miniprojecttodolist.ui.components.SecureTextField
import org.cheva.miniprojecttodolist.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    onNavigate: (Any) -> Unit
) {
    LaunchedEffect(state.successLogin) {
        if (state.successLogin) {
            delay(1000)
            onNavigate(DashboardScreen)
        }
    }

    Scaffold {
        if (state.message.isNotBlank()) {
            ResultDialog(
                isSuccess = state.successLogin,
                message = state.message,
                onDismiss = { onEvent(LoginEvent.OnDismissDialog) }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.login_headline),
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.login_hint),
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                label = { Text(stringResource(R.string.email_label)) },
                value = state.email,
                onValueChange = { onEvent(LoginEvent.OnEmailChanged(it)) },
                placeholder = { Text(stringResource(R.string.email_hint)) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
            )
            Spacer(modifier = Modifier.height(8.dp))
            SecureTextField(
                label = stringResource(R.string.password_label),
                value = state.password,
                onValueChange = { onEvent(LoginEvent.OnPasswordChanged(it)) },
                keyboardType = KeyboardType.Password,
                isVisible = state.passwordVisible,
                onVisibilityChange = { onEvent(LoginEvent.OnPasswordVisibilityChanged(it)) },
                hint = stringResource(R.string.password_hint)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onEvent(LoginEvent.OnLoginClicked) }
            ) {
                Text(stringResource(R.string.login_headline))
            }
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onNavigate(RegisterScreen) }
            ) {
                Text(stringResource(R.string.to_register))
            }
        }
    }
}