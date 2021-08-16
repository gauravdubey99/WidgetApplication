package com.example.widgetapplication.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.widgetapplication.constants.MINIMUM_PASSWORD_LENGTH
import com.example.widgetapplication.enums.PasswordCheckList

@Composable
fun PasswordWidget(
    placeholder: String,
    showPasswordValidationView: Boolean = false,
    password: String,
    onPasswordChanged: (String) -> Unit
) {

    val isPasswordInputInProgressOrValid = remember {
        mutableStateOf(false)
    }

    val passwordChecklist = remember {
        mutableListOf<PasswordCheckList>()
    }

    Column {
        OutlinedTextField(value = password, onValueChange = {
            checkValidation(it) { checkList ->
                passwordChecklist.addAll(checkList)
            }
            onPasswordChanged(it)
        },
        placeholder = {
            Text(text = placeholder)
        })

        BulletListWidget(textList = passwordChecklist, showPasswordValidationView)
    }
}

private fun checkValidation(password: String, checkList: (List<PasswordCheckList>) -> Unit)  {
    checkList(mutableListOf(isPasswordOfNeededLength(password)))
}

private fun isPasswordOfNeededLength(password: String): PasswordCheckList {
    val passwordCheck = PasswordCheckList.PASSWORD_LENGTH
    passwordCheck.isValid = password.length >= MINIMUM_PASSWORD_LENGTH
    return passwordCheck
}