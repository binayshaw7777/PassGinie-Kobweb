package com.binayshaw.passginie.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.Page
import com.binayshaw.passginie.components.layouts.PageLayout
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.silk.components.forms.Button
import org.jetbrains.compose.web.dom.CheckboxInput
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import passGenerator

@Page
@Composable
fun HomePage() {
    PageLayout("PassGinie") {
        val generatedPassword = remember {
            mutableStateOf("Fd9)4y3g")
        }
        val passwordLength = remember {
            mutableStateOf(8)
        }
        val shouldIncludeUppercase = remember {
            mutableStateOf(false)
        }
        val shouldIncludeLowercase = remember {
            mutableStateOf(true)
        }
        val shouldIncludeNumbers = remember {
            mutableStateOf(false)
        }
        val shouldIncludeSymbols = remember {
            mutableStateOf(false)
        }
        P()
        Text("Password length: ${passwordLength.value}")
        P()
        Row {
            Button(onClick = {
                if (passwordLength.value > 4) {
                    passwordLength.value -= 1
                }
            }) {
                Text("-")
            }

            Button(onClick = {
                if (passwordLength.value < 15) {
                    passwordLength.value += 1
                }
            }) {
                Text("+")
            }
        }
        P()
        Row(verticalAlignment = Alignment.CenterVertically) {
            CheckboxInput(
                checked = shouldIncludeUppercase.value,
                attrs = {
                    onClick {
                        shouldIncludeUppercase.value = shouldIncludeUppercase.value.not()
                    }
                }
            )
            Text("Include Uppercase")
        }
        P()
        Row(verticalAlignment = Alignment.CenterVertically) {
            CheckboxInput(
                checked = shouldIncludeLowercase.value,
                attrs = {
                    onClick {
                        shouldIncludeLowercase.value = shouldIncludeLowercase.value.not()
                    }
                }
            )
            Text("Include Lowercase")
        }
        P()
        Row(verticalAlignment = Alignment.CenterVertically) {
            CheckboxInput(
                checked = shouldIncludeNumbers.value,
                attrs = {
                    onClick {
                        shouldIncludeNumbers.value = shouldIncludeNumbers.value.not()
                    }
                }
            )
            Text("Include Numbers")
        }
        P()
        Row(verticalAlignment = Alignment.CenterVertically) {
            CheckboxInput(
                checked = shouldIncludeSymbols.value,
                attrs = {
                    onClick {
                        shouldIncludeSymbols.value = shouldIncludeSymbols.value.not()
                    }
                }
            )
            Text("Include Symbols")
        }

        P()
        Button(
            onClick = {
                generatedPassword.value = passGenerator(
                    passwordLength.value,
                    shouldIncludeUppercase.value,
                    shouldIncludeLowercase.value,
                    shouldIncludeNumbers.value,
                    shouldIncludeSymbols.value
                )
            }
        ) {
            Text("Generate")
        }
        P()
        Text("Your password is: ${generatedPassword.value}")
    }
}
