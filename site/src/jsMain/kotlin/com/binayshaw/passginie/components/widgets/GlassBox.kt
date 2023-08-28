package com.binayshaw.passginie.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.BackgroundClip
import com.varabyte.kobweb.compose.css.CSSBackground
import com.varabyte.kobweb.compose.css.MixBlendMode
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.css.functions.toImage
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.graphics.Image
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.rgba

@Composable
fun GlassBox(modifier: Modifier, content: @Composable () -> Unit) {
    Box(modifier = modifier
        .fillMaxWidth(50.percent)
        .backgroundClip(BackgroundClip.BorderBox)
        .borderRadius(20.px)
        .backgroundClip(BackgroundClip.ContentBox)
        .backgroundBlendMode(MixBlendMode.Overlay)
        .boxShadow(6.px, 4.px, 19.px, 0.px, rgba(0, 0, 0, 0.11f))
        .background(
            CSSBackground(image = linearGradient(LinearGradient.Direction.ToRight, rgba(255, 255, 255, 0.75), rgba(255, 255, 255, 0.40)).toImage()))
        .padding(2.px)
    ) {
        content()
        Image(
            modifier = Modifier.fillMaxSize().borderRadius(18.px),
            src = "images/OverlayMask.png"
        )
    }
}