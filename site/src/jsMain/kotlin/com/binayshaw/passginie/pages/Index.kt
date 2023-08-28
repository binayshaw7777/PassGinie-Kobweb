package com.binayshaw.passginie.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.Page
import com.binayshaw.passginie.components.layouts.PageLayout
import com.binayshaw.passginie.components.widgets.GlassBox
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.css.functions.toImage
import com.varabyte.kobweb.compose.dom.ElementTarget
import com.varabyte.kobweb.compose.foundation.layout.*
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowRotateLeft
import com.varabyte.kobweb.silk.components.icons.fa.FaCopy
import com.varabyte.kobweb.silk.components.overlay.KeepPopupOpenStrategy
import com.varabyte.kobweb.silk.components.overlay.Tooltip
import com.varabyte.kobweb.silk.components.overlay.manual
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.base
import kotlinx.browser.window
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.CheckboxInput
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.RangeInput
import org.jetbrains.compose.web.dom.Text
import passGenerator

@Page
@Composable
fun HomePage() {
    PageLayout(null) {
        val generatedPassword = remember {
            mutableStateOf(
                passGenerator(
                    8, false,
                    false, false,
                    false
                )
            )
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
        val showCopedPasswordToolTip = remember {
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
            modifier = Modifier
                .fillMaxSize()
                .padding(20.px)
                .scrollBehavior(ScrollBehavior.Smooth),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            GlassBox(modifier = Modifier.fillMaxHeight(20.percent)) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth(85.percent)
                        .fillMaxHeight(50.percent)
                        .zIndex(1)
                        .borderRadius(10.px)
                        .margin(20.px)
                ) {

                    Row(
                        modifier = Modifier.fillMaxSize().padding(20.px),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        P(attrs = {
                            style {
                                fontWeight(FontWeight.Bold)
                            }
                        }) {
                            Text(generatedPassword.value)
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(10.percent),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            FaCopy(modifier = Modifier.onClick {
                                window.navigator.clipboard.writeText(generatedPassword.value)
                                showCopedPasswordToolTip.value = true
                            })
                            Spacer()

                            FaArrowRotateLeft(modifier = Modifier.onClick {
                                regeneratePassword.value = true
                            })
                            if (showCopedPasswordToolTip.value) {
                                window.setTimeout({
                                    showCopedPasswordToolTip.value = false
                                }, 2000)
                            }
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(85.percent)
                        .fillMaxHeight(50.percent)
                        .styleModifier {
                            mixBlendMode(MixBlendMode.Overlay)
                        }
                        .background(rgba(255, 255, 255, 0.25))
                        .borderRadius(10.px)
                        .margin(20.px)
                )
            }
            Tooltip(
                ElementTarget.PreviousSibling,
                "Copied!",
                modifier = Modifier.opacity(
                    if (showCopedPasswordToolTip.value) 100.percent
                    else 0.percent
                ),
                hasArrow = false,
                keepOpenStrategy = KeepPopupOpenStrategy.manual(showCopedPasswordToolTip.value)
            )
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
                        modifier = Modifier.fillMaxWidth(70.percent),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text("-")
                        RangeInput(
                            value = passwordLength.value,
                            min = 4,
                            max = 15,
                            step = 1,
                            attrs = {
                                style {
                                    minWidth(200.px)
                                }
                                onInput {
                                    console.log("Slider value is: ${it.value}")
                                    passwordLength.value = it.value!!.toInt()
                                    regeneratePassword.value = true
                                }
                            }
                        )
                        Text("+")
                    }
                    Spacer()
                    Spacer()
                    Text("Include")
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
