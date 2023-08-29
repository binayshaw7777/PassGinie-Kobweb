package com.binayshaw.passginie.components.styles

import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.scale
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

val EditorBoxStyle by ComponentStyle {
    base {
        Modifier
            .fillMaxHeight(100.percent)
            .fillMaxWidth(95.percent)
            .fontSize(16.px)
    }

    Breakpoint.SM {
        Modifier
            .fillMaxHeight(80.percent)
            .fillMaxWidth(85.percent)
            .fontSize(18.px)
    }

    Breakpoint.MD {
        Modifier
            .fillMaxHeight(70.percent)
            .fillMaxWidth(50.percent)
            .fontSize(20.px)
    }
    Breakpoint.LG {
        Modifier
            .fillMaxHeight(65.percent)
            .fillMaxWidth(50.percent)
            .fontSize(20.px)
    }
}

val CheckBoxStyle by ComponentStyle {
    base {
        Modifier.scale(0.8)
    }
    Breakpoint.SM {
        Modifier.scale(0.9)
    }
    Breakpoint.MD {
        Modifier.scale(1)
    }
}