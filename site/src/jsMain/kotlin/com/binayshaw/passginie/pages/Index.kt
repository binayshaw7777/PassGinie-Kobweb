package com.binayshaw.passginie.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.Page
import com.binayshaw.passginie.components.layouts.PageLayout
import com.binayshaw.passginie.components.widgets.GlassBox
import com.varabyte.kobweb.compose.css.CSSBackground
import com.varabyte.kobweb.compose.css.CaretColor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.css.functions.opacity
import com.varabyte.kobweb.compose.css.functions.toImage
import com.varabyte.kobweb.compose.css.toBackgroundImage
import com.varabyte.kobweb.compose.foundation.layout.*
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowRotateLeft
import com.varabyte.kobweb.silk.components.icons.fa.FaCopy
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.base
import com.varabyte.kobweb.silk.components.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.CheckboxInput
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import passGenerator

@Page
@Composable
fun HomePage() {
    PageLayout(null) {
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
        val regeneratePassword = remember {
            mutableStateOf(false)
        }

        if (regeneratePassword.value) {
            generatedPassword.value = passGenerator(
                passwordLength.value,
                shouldIncludeUppercase.value,
                shouldIncludeLowercase.value,
                shouldIncludeNumbers.value,
                shouldIncludeSymbols.value
            )
            regeneratePassword.value = false
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(20.px),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            GlassBox(modifier = Modifier.fillMaxHeight(20.percent)) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth(70.percent)
                        .fillMaxHeight(50.percent)
                        .zIndex(1)
                        .background(rgba(255, 255, 255, 0.1))
                        .borderRadius(10.px)
                        .margin(20.px)
                ) {

                    Row(
                        modifier = Modifier.fillMaxSize().fontWeight(FontWeight.Bold),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(generatedPassword.value)

                        Row(
                            modifier = Modifier.fillMaxWidth(10.percent),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            FaCopy(modifier = Modifier.onClick {
                                // TODO: Perform Copy to Clipboard onClick
                            })
                            Spacer()

                            FaArrowRotateLeft(modifier = Modifier.onClick {
                                regeneratePassword.value = true
                            })
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.white)
                        .opacity(0.01)
                        .borderRadius(2.percent)
                        .zIndex(-1)
                )
            }
            P()
            GlassBox(modifier = Modifier.fillMaxHeight(50.percent)) {
                Column(
                    modifier = Modifier.fillMaxSize().zIndex(1),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Spacer()
                    Text("Password length: ${passwordLength.value}")
                    Spacer()
                    Row(
                        modifier = Modifier.fillMaxWidth(25.percent),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(onClick = {
                            if (passwordLength.value > 4) {
                                passwordLength.value -= 1
                                regeneratePassword.value = true
                            }
                        }) {
                            Text("-")
                        }

                        Button(onClick = {
                            if (passwordLength.value < 15) {
                                passwordLength.value += 1
                                regeneratePassword.value = true
                            }
                        }) {
                            Text("+")
                        }
                    }
                    Spacer()
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CheckboxInput(
                            checked = shouldIncludeUppercase.value,
                            attrs = {
                                onClick {
                                    shouldIncludeUppercase.value = shouldIncludeUppercase.value.not()
                                    regeneratePassword.value = true
                                }
                            }
                        )
                        Text("Include Uppercase")
                    }
                    Spacer()
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CheckboxInput(
                            checked = shouldIncludeLowercase.value,
                            attrs = {
                                onClick {
                                    shouldIncludeLowercase.value = shouldIncludeLowercase.value.not()
                                    regeneratePassword.value = true
                                }
                            }
                        )
                        Text("Include Lowercase")
                    }
                    Spacer()
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CheckboxInput(
                            checked = shouldIncludeNumbers.value,
                            attrs = {
                                onClick {
                                    shouldIncludeNumbers.value = shouldIncludeNumbers.value.not()
                                    regeneratePassword.value = true
                                }
                            }
                        )
                        Text("Include Numbers")
                    }
                    Spacer()
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CheckboxInput(
                            checked = shouldIncludeSymbols.value,
                            attrs = {
                                onClick {
                                    shouldIncludeSymbols.value = shouldIncludeSymbols.value.not()
                                    regeneratePassword.value = true
                                }
                            }
                        )
                        Text("Include Symbols")
                    }
                    Spacer()
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.white)
                        .opacity(0.01)
                        .borderRadius(2.percent)
                        .zIndex(-1)
                )
            }
        }
    }

}


val ModuleBorderWrapStyle by ComponentStyle.base {
    Modifier
        .maxWidth(250.px)
        .padding(3.px)
        .position(Position.Relative)
        .background(
            CSSBackground(
                image = linearGradient(
                    LinearGradient.Direction.ToRight,
                    Color.white,
                    Color.lightgray
                ).toImage()
            )
        )
}

val GradientBorderStyle by ComponentStyle.base {
    Modifier
        .border(1.px, LineStyle.Solid, Color.transparent)
        .background(
            CSSBackground(
                image = linearGradient(45.deg, Color.white, Color.transparent).toImage()
            )
        )
        .size(200.px)
}
