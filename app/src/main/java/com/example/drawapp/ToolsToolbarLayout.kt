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

    fun render(list: List<ToolItem>) {
        adapterDelegate.items = list
    }

    fun setOnClickListener(onClick: (ToolItem) -> Unit) {
        this.onClick = onClick
    }
}