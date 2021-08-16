package com.example.widgetapplication.enums

enum class PasswordCheckList(val validationText: String, var isValid: Boolean) {
    PASSWORD_LENGTH("Password must be of at-least 8 characters", false),
    ONE_UPPER_LOWER_CASE("Password must have one upper and one lower case character", false),
    MUST_HAVE_SPECIAL_CHARACTER("Password must have one Special character", false)
}
