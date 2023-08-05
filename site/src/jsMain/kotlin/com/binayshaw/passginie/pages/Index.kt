package com.binayshaw.passginie.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.Page
import com.binayshaw.passginie.components.layouts.PageLayout
import com.varabyte.kobweb.silk.components.forms.Button
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.dom.Input
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
        P()
        Button(
            onClick = {
                generatedPassword.value = passGenerator(
                    8,
                    false,
                    false,
                    false,
                    false
                )
            }
        ) {
            Text("Generate")
        }
        P()
        Text("Your password is: ${generatedPassword.value}")
    }
}
