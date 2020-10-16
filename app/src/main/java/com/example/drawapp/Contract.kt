package com.example.drawapp

import com.example.drawapp.base.Event

data class ViewState(
    val toolsList: List<ToolItem.ToolModel>,
    val colorList: List<ToolItem.ColorModel>,
    val sizeList: List<ToolItem.SizeModel>,
    val styleList: List<ToolItem.StyleModel>,
    val canvasViewState: CanvasViewState,
    val isPaletteVisible: Boolean,
    val isBrushSizeChangerVisible: Boolean,
    val isToolsVisible: Boolean,
    val isStyleVisible: Boolean
)

sealed class UiEvent() : Event {
    data class OnColorClick(val index: Int) : UiEvent()
    data class OnSizeClick(val index: Int) : UiEvent()
    data class OnStyleClick(val index: Int) : UiEvent()
    data class OnToolsClick(val index: Int) : UiEvent()
    object OnToolbarClicked : UiEvent()
    object OnDrawClick : UiEvent()
}
