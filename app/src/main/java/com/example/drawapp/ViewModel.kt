package com.example.drawapp

import com.example.drawapp.base.BaseViewModel
import com.example.drawapp.base.Event

class ViewModel : BaseViewModel<ViewState>() {

    override fun initialViewState(): ViewState = ViewState(
        toolsList = enumValues<TOOLS>().map { ToolItem.ToolModel(
            it.value,
            it.tool,
            COLOR.BLACK.value
        ) },
        colorList = enumValues<COLOR>().map { ToolItem.ColorModel(it.value) },
        sizeList = enumValues<SIZE>().map { ToolItem.SizeModel(it.value) },
        styleList = enumValues<STYLE>().map { ToolItem.StyleModel(it.value) },
        canvasViewState = CanvasViewState(COLOR.BLACK, SIZE.SMALL, STYLE.STROKE),
        isPaletteVisible = false,
        isBrushSizeChangerVisible = false,
        isToolsVisible = false,
        isStyleVisible = false
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
            is UiEvent.OnStyleClick -> {
                return previousState.copy(
                    canvasViewState = previousState.canvasViewState.copy(
                        style = STYLE.from(previousState.styleList[event.index].type)
                    )
                )
            }
            is UiEvent.OnToolsClick -> {
                return when ((event.tool as ToolItem.ToolModel).type) {
                    Tool.STYLE -> {
                        previousState.copy(
                            isStyleVisible = true,
                            isPaletteVisible = false,
                            isBrushSizeChangerVisible = false
                        )
                    }
                    Tool.SIZE -> {
                        previousState.copy(
                            isStyleVisible = false,
                            isPaletteVisible = false,
                            isBrushSizeChangerVisible = true
                        )
                    }
                    Tool.COLOR -> {
                        previousState.copy(
                            isStyleVisible = false,
                            isPaletteVisible = true,
                            isBrushSizeChangerVisible = false
                        )
                    }
                }
            }
            is UiEvent.OnToolbarClicked -> {
                return previousState.copy(
                    isToolsVisible = !previousState.isToolsVisible
                )
            }
            is UiEvent.OnDrawClick -> {
                return previousState.copy(
                    isStyleVisible = false,
                    isPaletteVisible = false,
                    isBrushSizeChangerVisible = false,
                    isToolsVisible = false
                )
            }
        }
        return null
    }
}