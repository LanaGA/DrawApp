package com.example.drawapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.drawapp.base.showIf
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_tools.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    val viewModel: ViewModel by viewModel()
    val adapterDelegate = ListDelegationAdapter(
        paletteAdapterDelegate()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.viewState.observe(this, Observer(::render))
        openPalette.setOnClickListener{
            viewModel.processUiEvent(UiEvent.OnToolbarClicked)
        }
        (palette as )
    }

    private fun render(viewState: ViewState){
        palette.showIf(viewState.isPaletteVisible)
    }

}

