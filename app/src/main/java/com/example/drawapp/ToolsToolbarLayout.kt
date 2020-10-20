package com.example.drawapp

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.android.synthetic.main.view_tools.view.*

class ToolsToolbarLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private var onClick: (ToolItem) -> Unit = {}

    private val adapterDelegate = ListDelegationAdapter(
        toolsAdapterDelegate {
            onClick(it)
        }
    )

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        toolsList.adapter = adapterDelegate
        toolsList.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    fun render(list: List<ToolItem>,  viewState: ViewState) {
        val canvasViewState = viewState.canvasViewState
        list.forEach { toolItem ->
            if (toolItem is ToolItem.ToolModel && toolItem.type == Tool.COLOR) {
                toolItem.currentColor = canvasViewState.color.value
            }
            if (toolItem is ToolItem.ToolModel && toolItem.type == Tool.SIZE) {

            }
            if(toolItem is ToolItem.ToolModel && toolItem.type == Tool.STYLE){

            }
        }
        adapterDelegate.items = list
        adapterDelegate.notifyDataSetChanged()
    }

    fun setOnClickListener(onClick: (ToolItem) -> Unit) {
        this.onClick = onClick
    }
}