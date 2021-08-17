package com.example.widgetapplication.constants

import java.util.regex.Pattern

const val MINIMUM_PASSWORD_LENGTH = 8

//Regex Pattern
val REGEX_AT_LEAST_ONE_CAPS_ONE_SMALL_ONE_NUMBER : Pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")
val REGEX_AT_LEAST_ONE_SPECIAL_CHARACTER : Pattern = Pattern.compile("^(?=.*[@#\$%^&+=]).*$")