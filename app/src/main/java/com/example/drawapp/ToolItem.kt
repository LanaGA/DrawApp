package com.example.drawapp

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.example.drawapp.base.Item

sealed class ToolItem : Item {
    data class ColorModel(@ColorRes val color: Int) : ToolItem()
    data class SizeModel(val size: Int): ToolItem()
    data class ToolModel(@DrawableRes val icon: Int): ToolItem()
    data class StyleModel(val type: Int): ToolItem()
}