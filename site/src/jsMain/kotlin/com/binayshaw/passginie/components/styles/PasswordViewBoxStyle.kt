package com.binayshaw.passginie.components.styles

import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Br

val PasswordViewBoxStyle by ComponentStyle {
    base {
        Modifier
            .fillMaxHeight(20.percent)
            .fillMaxWidth(95.percent)
            .fontSize(18.px)
    }

    Breakpoint.SM {
        Modifier
            .fillMaxHeight(15.percent)
            .fillMaxWidth(85.percent)
            .fontSize(20.px)
    }

    Breakpoint.MD {
        Modifier
            .fillMaxHeight(20.percent)
            .fillMaxWidth(50.percent)
            .fontSize(24.px)
    }
}

val AccessibilityButtonStyle by ComponentStyle {
    base {
      Modifier.margin(10.px)
          .scale(1)
    }
    Breakpoint.SM {
        Modifier.margin(8.px)
            .scale(1)
    }
    Breakpoint.MD {
        Modifier.margin(5.px)
            .scale(1.1)
    }
}

