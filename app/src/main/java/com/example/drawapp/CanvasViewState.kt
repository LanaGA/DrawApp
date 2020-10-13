package com.example.drawapp

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class CanvasViewState(val color: COLOR, val size: SIZE)

enum class COLOR(
    @ColorRes
    val value: Int
) {

    BLACK(R.color.colorPaintBlack),
    RED(R.color.colorPaintRed),
    BLUE(R.color.colorPaintBlue);

    companion object {
        private val map = values().associateBy(COLOR::value)
        fun from(color: Int) = map[color] ?: BLACK
    }
}

enum class SIZE(
    val value: Int
) {
    SMALL(4),
    MEDIUM(16),
    LARGE(32);

    companion object {
        private val map = values().associateBy(SIZE::value)
        fun from(size: Int) = map[size] ?: SMALL
    }
}

enum class TOOLS(
    @DrawableRes
    val value: Int
) {
    NORMAL(R.drawable.ic_baseline_brush_24),
    STROKE(R.drawable.ic_baseline_brush_24),
    SIZE(R.drawable.ic_baseline_brush_24),
    PALETTE(R.drawable.ic_baseline_brightness_1_24)
}

