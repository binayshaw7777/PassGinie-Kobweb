package com.binayshaw.passginie.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.BackgroundClip
import com.varabyte.kobweb.compose.css.CSSBackground
import com.varabyte.kobweb.compose.css.MixBlendMode
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.css.functions.toImage
import com.varabyte.kobweb.compose.css.mixBlendMode
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.BoxScope
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.graphics.Image
import org.jetbrains.compose.web.css.*

@Composable
fun GlassBox(modifier: Modifier, content: @Composable BoxScope.() -> Unit) {
    Box(
        modifier = modifier.fillMaxWidth(50.percent),
        contentAlignment = Alignment.Center
    ) {
        content()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .margin(2.px)
                .borderRadius(20.px)
                .border(width = 2.px, style = LineStyle.Solid, color = Color.white)
                .styleModifier {
                    mixBlendMode(MixBlendMode.Overlay)
                }
                .boxShadow(6.px, 4.px, 19.px, 0.px, rgba(0, 0, 0, 0.11f))
        )
    }
}