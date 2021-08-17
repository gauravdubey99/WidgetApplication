package com.example.widgetapplication.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.widgetapplication.constants.MINIMUM_PASSWORD_LENGTH
import com.example.widgetapplication.constants.REGEX_AT_LEAST_ONE_CAPS_ONE_SMALL_ONE_NUMBER
import com.example.widgetapplication.constants.REGEX_AT_LEAST_ONE_SPECIAL_CHARACTER
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

    val passwordVisibility = remember {
        mutableStateOf(false)
    }

    Column {
        OutlinedTextField(
            value = password,
            onValueChange = {
                checkValidation(it) { checkList ->
                    passwordChecklist.clear()
                    passwordChecklist.addAll(checkList)
                }
                onPasswordChanged(it)
            },
            placeholder = {
                Text(text = placeholder)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_dialog_info),
                    contentDescription = "Icon to Toggle the visibility of Password field",
                    modifier = Modifier
                        .clickable {
                            passwordVisibility.value = !passwordVisibility.value
                        }
                        .alpha(if (passwordVisibility.value) 0.5f else 1f)
                )
            },
            visualTransformation = if(passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation()
        )

        BulletListWidget(textList = passwordChecklist, showPasswordValidationView)
    }
}

private fun checkValidation(password: String, checkList: (List<PasswordCheckList>) -> Unit) {
    checkList(
        mutableListOf(
            isPasswordOfNeededLength(password),
            doPasswordHaveOneCapitalAndSmallLetter(password),
            checkIfASpecialCharacterExist(password)
        )
    )
}

private fun checkIfASpecialCharacterExist(password: String): PasswordCheckList {
    val passwordCheck = PasswordCheckList.MUST_HAVE_SPECIAL_CHARACTER
    passwordCheck.isValid = REGEX_AT_LEAST_ONE_SPECIAL_CHARACTER.matcher(password).matches()
    return passwordCheck
}

private fun doPasswordHaveOneCapitalAndSmallLetter(password: String): PasswordCheckList {
    val passwordCheck = PasswordCheckList.ONE_UPPER_LOWER_CASE_ONE_NUMBER
    passwordCheck.isValid = REGEX_AT_LEAST_ONE_CAPS_ONE_SMALL_ONE_NUMBER.matcher(password).matches()
    return passwordCheck
}

private fun isPasswordOfNeededLength(password: String): PasswordCheckList {
    val passwordCheck = PasswordCheckList.PASSWORD_LENGTH
    passwordCheck.isValid = password.length >= MINIMUM_PASSWORD_LENGTH
    return passwordCheck
}