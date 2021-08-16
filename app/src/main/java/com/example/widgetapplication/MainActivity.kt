package com.example.widgetapplication

import com.example.widgetapplication.widgets.EmailWidget
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.widgetapplication.ui.theme.WidgetApplicationTheme
import com.example.widgetapplication.widgets.PasswordWidget

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                CreateForm()
            }
        }
    }
}

@Composable
fun MyApp(compose : @Composable () -> Unit) {
    WidgetApplicationTheme {
        compose()
    }
}

@Composable
fun CreateForm() {
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    Column {
        EmailWidget(email = emailState.value, onEmailUpdate = {
            emailState.value = it
        })

        PasswordWidget(placeholder = "Password", password = passwordState.value, onPasswordChanged = {
            passwordState.value = it
        }, showPasswordValidationView = true)
    }
}

@Preview
@Composable
fun PreviewCompose() {
    MyApp {
        CreateForm()
    }
}