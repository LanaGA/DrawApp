package com.example.drawapp

import androidx.annotation.ColorRes

data class CanvasViewState(val color: COLOR)

enum class COLOR(
    @ColorRes
    val value: Int
) {
    BLACK(R.color.colorPaintBlack),
    RED(R.color.colorPaintRed),
    BLUE(R.color.colorPaintBlue)
}