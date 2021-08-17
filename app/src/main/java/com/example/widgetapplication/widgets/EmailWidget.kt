package com.example.widgetapplication.widgets

import android.util.Patterns
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun EmailWidget(email: String, onEmailUpdate: (String) -> Unit) {
    val isEmailEditInProgressOrProperFormat = remember {
        mutableStateOf(true)
    }

    val indicatorColor = if (isEmailEditInProgressOrProperFormat.value) Color.Blue else Color.Red

    OutlinedTextField(
        value = email,
        onValueChange = {
            isEmailEditInProgressOrProperFormat.value = true
            onEmailUpdate(it)
        },
        textStyle = TextStyle(color = indicatorColor, fontSize = 20.sp),
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                isEmailEditInProgressOrProperFormat.value = email.isEmpty() || isEmailValid(email)
            },
        label = {
            Text(
                text = "E-mail",
                style = TextStyle(color = indicatorColor)
            )
        },
        placeholder = {
            Text(text = "Please enter your E-mail address!")
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = indicatorColor,
            focusedLabelColor = indicatorColor,
            unfocusedBorderColor = indicatorColor,
            unfocusedLabelColor = indicatorColor
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        )
    )
}

private fun isEmailValid(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

@Preview
@Composable
fun PreviewEmail() {
    EmailWidget(email = "", onEmailUpdate = {

    })
}