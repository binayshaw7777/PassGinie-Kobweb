package com.binayshaw.passginie.components.layouts

import androidx.compose.runtime.*
import com.binayshaw.passginie.Utils.Fonts.POPPINS
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import kotlinx.browser.document
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.silk.components.graphics.Image

@Composable
fun PageLayout(title: String?, content: @Composable () -> Unit) {
    LaunchedEffect(title) {
        title?.let {
            document.title = it
        }

    }

    Box(
        Modifier
            .fillMaxWidth()
            .minHeight(100.percent)
            .color(Color.white)
            .fontFamily(POPPINS)
            // Create a box with two rows: the main content (fills as much space as it can) and the footer (which reserves
            // space at the bottom). "min-content" means the use the height of the row, which we use for the footer.
            // Since this box is set to *at least* 100%, the footer will always appear at least on the bottom but can be
            // pushed further down if the first row grows beyond the page.
            .gridTemplateRows { size(1.fr); size(minContent) }
    ) {
        BackgroundGradientImage()
        Column(
            modifier = Modifier.fillMaxSize().textAlign(TextAlign.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            title?.let {
                H1 { Text(it) }
            }
            content()
        }
    }
}

@Composable
fun BackgroundGradientImage() {
    Image(
        modifier = Modifier.fillMaxSize(),
        src = "images/Background.png"
    )
}
