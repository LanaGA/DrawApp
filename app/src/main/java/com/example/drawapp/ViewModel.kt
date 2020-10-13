package com.example.drawapp

import com.example.drawapp.base.BaseViewModel
import com.example.drawapp.base.Event

class ViewModel : BaseViewModel<ViewState>() {

    override fun initialViewState(): ViewState = ViewState(
        toolsList = enumValues<TOOLS>().map { ToolItem.ToolModel(it.value) },
        colorList = enumValues<COLOR>().map { ToolItem.ColorModel(it.value) },
        sizeList = enumValues<SIZE>().map { ToolItem.SizeModel(it.value) },
        canvasViewState = CanvasViewState(COLOR.BLACK, SIZE.SMALL),
        isPaletteVisible = false,
        isBrushSizeChangerVisible = false,
        isToolsVisible = false
    )

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.OnColorClick -> {
                return previousState.copy(
                    canvasViewState = previousState.canvasViewState.copy(
                        color = COLOR.from(previousState.colorList[event.index].color),
                    )
                )
            }
            is UiEvent.OnSizeClick -> {
                return previousState.copy(
                    canvasViewState = previousState.canvasViewState.copy(
                        size = SIZE.from(previousState.sizeList[event.index].size),
                    )
                )
            }
            is UiEvent.OnToolsClick -> {
                return when (event.index) {
                    2 -> {
                        previousState.copy(
                            isPaletteVisible = false,
                            isBrushSizeChangerVisible = true
                        )
                    }
                    3 -> {
                        previousState.copy(
                            isPaletteVisible = true,
                            isBrushSizeChangerVisible = false
                        )
                    }
                    else -> {
                        return null
                    }
                }
            }
            is UiEvent.OnToolbarClicked -> {
                return previousState.copy(
                    isToolsVisible = !previousState.isToolsVisible
                )
            }
        }
        return null
    }
}