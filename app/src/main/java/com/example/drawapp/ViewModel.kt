package com.example.drawapp

import com.example.drawapp.base.BaseViewModel
import com.example.drawapp.base.Event

class ViewModel : BaseViewModel<ViewState>() {
    override fun initialViewState(): ViewState = ViewState(
        colorList = enumValues<COLOR>().map { ColorModel(it.value) },
        canvasViewState = CanvasViewState(COLOR.BLACK),
        isPaletteVisible = false
    )

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.OnPaletteClicked -> {

            }
            is UiEvent.OnToolbarClicked -> {
                previousState.copy(
                    isPaletteVisible = !previousState.isPaletteVisible
                )
            }
        }
        return null
    }

}