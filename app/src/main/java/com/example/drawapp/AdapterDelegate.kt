package com.example.drawapp

import android.graphics.PorterDuff
import com.example.drawapp.base.Item
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import kotlinx.android.synthetic.main.item_palette.view.*

fun paletteAdapterDelegate(): AdapterDelegate<List<Item>> =
    adapterDelegateLayoutContainer<ColorModel, Item>(
        R.layout.item_palette
    ) {
        bind {
            itemView.color.setColorFilter(item.color, PorterDuff.Mode.SRC_IN)
        }
    }