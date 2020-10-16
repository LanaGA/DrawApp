package com.example.drawapp

import android.graphics.Paint
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class CanvasViewState(val color: COLOR, val size: SIZE, val style: STYLE)

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
    STROKE(R.drawable.ic_baseline_brush_24),
    SIZE(R.drawable.ic_baseline_format_size_24),
    PALETTE(R.drawable.ic_baseline_palette_24);

    companion object {
        private val map = values().associateBy(TOOLS::value)
        fun from(tool: Int) = map[tool] ?: PALETTE
    }
}

enum class STYLE(val value: Int){
    FILL(0),
    STROKE(1),
    FILL_AND_STROKE(2);

    companion object {
        private val map = values().associateBy(STYLE::value)
        fun from(style: Int) = map[style] ?: STROKE
    }
}