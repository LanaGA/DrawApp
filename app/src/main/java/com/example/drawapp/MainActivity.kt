package com.example.drawapp

import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.drawapp.base.checkPermissionForWriteToStorage
import com.example.drawapp.base.requestPermissionForWriteToStorage
import com.example.drawapp.base.showIf
import com.example.drawapp.savecomponent.SaveService
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: ViewModel by viewModel()
    private lateinit var toolsLayouts: List<ToolsLayout>
    private lateinit var tools: ToolsToolbarLayout
    private lateinit var saveService: SaveService
    private val connection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, bunder: IBinder?) {
            saveService = (bunder as SaveService.MyServiceBinder).getService()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolsLayouts = listOf(
            palette as ToolsLayout,
            size as ToolsLayout,
            style as ToolsLayout
        )
        toolsLayouts[0].setOnClickListener {
            viewModel.processUiEvent(UiEvent.OnColorClick(it))
        }
        toolsLayouts[1].setOnClickListener {
            viewModel.processUiEvent(UiEvent.OnSizeClick(it))
        }
        toolsLayouts[2].setOnClickListener {
            viewModel.processUiEvent(UiEvent.OnStyleClick(it))
        }

        tools = toolsToolbar as ToolsToolbarLayout
        tools.setOnClickListener {
            viewModel.processUiEvent(UiEvent.OnToolsClick(it))
        }


        viewModel.viewState.observe(this, Observer(::render))
        openPalette.setOnClickListener {
            viewModel.processUiEvent(UiEvent.OnToolbarClicked)
        }
        drawView.setOnClickListener {
            viewModel.processUiEvent(UiEvent.OnDrawClick)
        }

        Intent(this, SaveService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }

        clearImage.setOnClickListener {
            drawView.clear()
        }

        saveImage.setOnClickListener {
            if (checkPermissionForWriteToStorage()) {
                saveImage()
            } else {
                requestPermissionForWriteToStorage()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (checkPermissionForWriteToStorage()) {
            saveImage()
        }
        else
        {
            Toast.makeText(this, "Операция невозможна из-за отсутствия разрешения", Toast.LENGTH_LONG).show()
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
    }

    private fun saveImage() = saveService.saveBitmap(drawView.getBitmap())

    private fun render(viewState: ViewState) {
        toolsLayouts[0].showIf(viewState.isPaletteVisible)
        toolsLayouts[0].render(viewState.colorList)

        toolsLayouts[1].showIf(viewState.isBrushSizeChangerVisible)
        toolsLayouts[1].render(viewState.sizeList)

        toolsLayouts[2].showIf(viewState.isStyleVisible)
        toolsLayouts[2].render(viewState.styleList)

        tools.showIf(viewState.isToolsVisible)
        tools.render(viewState.toolsList, viewState)


        drawView.render(viewState.canvasViewState)
    }
}

