package com.binayshaw.passginie.components.widgets

import androidx.compose.runtime.Composable
import com.binayshaw.passginie.Utils.Res
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.silk.components.graphics.Image

@Composable
fun CustomCheckbox(isChecked: Boolean) {
    Image(
        src = if (isChecked) {
            Res.Images.CHECKBOX_TRUE
        } else {
            Res.Images.CHECKBOX_FALSE
        }
    )
}

/**
 * CustomCheckbox(
 *      isChecked = false,
 *      description = null,
 *      uncheckedRes = "images/checkbox-false.svg",
 *      checkedRes = "images/checkbox-true.svg",
 *      onClick = { //it: Boolean
 *  })
 * */

@Composable
fun CustomCheckBox(
    isChecked: Boolean = false,
    description: String?,
    uncheckedRes: String,
    checkedRes: String,
    onClick: (Boolean) -> Unit
) {
    Image(
        src = if (isChecked) {
            checkedRes
        } else {
            uncheckedRes
        },
        modifier = Modifier.onClick {
            onClick(isChecked.not())
        },
        desc = description ?: ""
    )
}