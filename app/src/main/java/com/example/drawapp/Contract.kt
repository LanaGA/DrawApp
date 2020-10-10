package com.example.drawapp

import com.example.drawapp.base.Event

data class ViewState(
    val colorList: List<ColorModel>,
    val canvasViewState: CanvasViewState,
    val isPaletteVisible: Boolean
)

sealed class UiEvent() : Event{
    data class OnPaletteClicked(val index: Int) : UiEvent()
    object OnToolbarClicked: UiEvent()
}
