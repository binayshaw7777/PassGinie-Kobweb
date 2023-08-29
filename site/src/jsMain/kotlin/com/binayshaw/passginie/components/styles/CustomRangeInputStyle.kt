package com.binayshaw.passginie.components.styles

import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.style.ComponentStyle

val CustomRangeInputStyle by ComponentStyle {
    base { Modifier.styleModifier {
        property("-webkit-appearance", "none")
        property("appearance", "none")
        property("background", "transparent")
        property("cursor", "pointer")
        property("width", "20rem")
    } }

    cssRule("::-webkit-slider-runnable-track") { Modifier.styleModifier {
        property("background-color", "rgb(220, 220, 220)")
        property("box-shadow", "0px 0px 2px 2px rgba(255, 255, 255, 0.1)")
        property("height", "2px")
        property("border-radius", "1px")
    } }

    cssRule("::after") { Modifier.styleModifier {
        property("content", "'+'")
        property("font-size", "20px")
        property("margin-left", "20px")
        property("color", "white")
    } }

    cssRule("::before") { Modifier.styleModifier {
        property("content", "'−'")
        property("font-size", "20px")
        property("margin-right", "20px")
        property("color", "white")
    } }

    cssRule("::-webkit-slider-thumb") { Modifier.styleModifier {
        property("-webkit-appearance", "none")
        property("appearance", "none")
        property("margin-top", "-10px")
        property("background-color", "white")
        property("height", "20px")
        property("width", "20px")
        property("border-radius", "10px")
        property("box-shadow", "0px 2px 2px 2px rgba(0, 0, 0, 0.2)")
        property("border", "1px solid rgba(0, 0, 0, 0.1)")
    } }
}