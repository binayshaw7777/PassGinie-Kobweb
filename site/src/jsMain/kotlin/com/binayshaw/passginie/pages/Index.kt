package com.binayshaw.passginie.pages

import androidx.compose.runtime.*
import com.binayshaw.passginie.Utils.Constants.PASSWORD_MAX_LENGTH
import com.binayshaw.passginie.Utils.Constants.PASSWORD_MIN_LENGTH
import com.binayshaw.passginie.Utils.Res
import com.varabyte.kobweb.core.Page
import com.binayshaw.passginie.components.layouts.PageLayout
import com.binayshaw.passginie.components.styles.*
import com.binayshaw.passginie.components.widgets.CustomCheckBox
import com.binayshaw.passginie.components.widgets.GlassBox
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.dom.ElementTarget
import com.varabyte.kobweb.compose.foundation.layout.*
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowRotateLeft
import com.varabyte.kobweb.silk.components.icons.fa.FaCheck
import com.varabyte.kobweb.silk.components.icons.fa.FaCopy
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.overlay.KeepPopupOpenStrategy
import com.varabyte.kobweb.silk.components.overlay.Tooltip
import com.varabyte.kobweb.silk.components.overlay.manual
import com.varabyte.kobweb.silk.components.style.toModifier
import kotlinx.browser.window
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.RangeInput
import org.jetbrains.compose.web.dom.Text
import passGenerator

@Page
@Composable
fun HomePage() {
    PageLayout(null) {

        /**
         * Variables Declaration
         */
        val generatedPassword = remember {
            mutableStateOf(
                passGenerator(
                    8,
                    shouldIncludeUppercase = false,
                    shouldIncludeLowercase = false,
                    shouldIncludeNumbers = false,
                    shouldIncludeSymbols = false
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

        /**
         * UI Logic
         */

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.px)
                .scrollBehavior(ScrollBehavior.Smooth),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            GlassBox(modifier = PasswordViewBoxStyle.toModifier()) {

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

                            }
                        }) {
                            Text(generatedPassword.value)
                        }

                        Row(
                            modifier = AccessibilityButtonStyle.toModifier().then(Modifier.fillMaxWidth(20.percent)),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            if (showCopedPasswordToolTip.value) {
                                FaCheck(modifier = AccessibilityButtonStyle.toModifier())
                            } else {
                                FaCopy(modifier = AccessibilityButtonStyle.toModifier().then(Modifier.onClick {
                                    window.navigator.clipboard.writeText(generatedPassword.value)
                                    showCopedPasswordToolTip.value = true
                                }))
                            }

                            FaArrowRotateLeft(modifier = AccessibilityButtonStyle.toModifier().then(Modifier.onClick {
                                regeneratePassword.value = true
                            }))
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
                        .background(rgba(255, 255, 255, 0.20))
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

            GlassBox(modifier = EditorBoxStyle.toModifier()) {

                Column(
                    modifier = Modifier.fillMaxSize().zIndex(1),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Spacer()

                    Row(
                        modifier = Modifier.fillMaxWidth(83.percent),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        P(attrs = {
                            style {
                                padding(5.px)
                            }
                        }) {
                            Text("Password length:")
                        }
                        P(attrs = {
                            style {
                                padding(5.px)
                                fontWeight(FontWeight.Bold)
                            }
                        }) {
                            Text(passwordLength.value.toString())
                        }
                    }

                    RangeInput(
                        value = passwordLength.value,
                        min = PASSWORD_MIN_LENGTH,
                        max = PASSWORD_MAX_LENGTH,
                        step = 1,
                        attrs = CustomRangeInputStyle.toModifier()
                            .then(Modifier.width(83.percent))
                            .toAttrs {
                                onInput {
                                    console.log("Slider value is: ${it.value}")
                                    passwordLength.value = it.value!!.toInt()
                                    regeneratePassword.value = true
                                }
                            }
                    )

                    Spacer()

                    Row(
                        modifier = Modifier.fillMaxWidth(83.percent),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        P(attrs = {
                            style {

                            }
                        }) {
                            Text("Include")
                        }
                    }

                    SimpleGrid(
                        numColumns(1, sm = 1, md = 1, lg = 2, xl = 2),
                        modifier = Modifier.fillMaxWidth(90.percent)
                    ) {

                        Column(modifier = Modifier.margin(0.px, 15.px)) {

                            Row(
                                modifier = Modifier.fillMaxWidth(65.percent).margin(0.px, 5.px)
                                    .onClick {
                                        shouldIncludeUppercase.value = shouldIncludeUppercase.value.not()
                                        regeneratePassword.value = true
                                    },
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {

                                CustomCheckBox(
                                    isChecked = shouldIncludeUppercase.value,
                                    modifier = CheckBoxStyle.toModifier(),
                                    uncheckedRes = Res.Images.CHECKBOX_FALSE,
                                    checkedRes = Res.Images.CHECKBOX_TRUE,
                                    onClick = {})

                                P(attrs = { style { } }) {
                                    Text("Uppercase")
                                }
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(65.percent).margin(0.px, 5.px)
                                    .onClick {
                                        shouldIncludeLowercase.value = shouldIncludeLowercase.value.not()
                                        regeneratePassword.value = true
                                    },
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {

                                CustomCheckBox(
                                    isChecked = shouldIncludeLowercase.value,
                                    modifier = CheckBoxStyle.toModifier(),
                                    uncheckedRes = Res.Images.CHECKBOX_FALSE,
                                    checkedRes = Res.Images.CHECKBOX_TRUE,
                                    onClick = {})

                                P(attrs = { style { } }) {
                                    Text("Lowercase")
                                }
                            }
                        }
                        Column(modifier = Modifier.margin(0.px, 15.px)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(65.percent).margin(0.px, 5.px)
                                    .onClick {
                                        shouldIncludeNumbers.value = shouldIncludeNumbers.value.not()
                                        regeneratePassword.value = true
                                    },
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {

                                CustomCheckBox(
                                    isChecked = shouldIncludeNumbers.value,
                                    modifier = CheckBoxStyle.toModifier(),
                                    uncheckedRes = Res.Images.CHECKBOX_FALSE,
                                    checkedRes = Res.Images.CHECKBOX_TRUE,
                                    onClick = {})

                                P(attrs = { style { } }) {
                                    Text("Numbers")
                                }
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(65.percent).margin(0.px, 5.px)
                                    .onClick {
                                        shouldIncludeSymbols.value = shouldIncludeSymbols.value.not()
                                        regeneratePassword.value = true
                                    },
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {

                                CustomCheckBox(
                                    isChecked = shouldIncludeSymbols.value,
                                    modifier = CheckBoxStyle.toModifier(),
                                    uncheckedRes = Res.Images.CHECKBOX_FALSE,
                                    checkedRes = Res.Images.CHECKBOX_TRUE,
                                    onClick = {})

                                P(attrs = { style { } }) {
                                    Text("Symbols")
                                }
                            }
                        }
                    }
                    Spacer()
                }
            }
        }
    }
}