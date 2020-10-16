package com.example.drawapp.koin

import android.app.Application
import com.example.drawapp.ImageSaver
import com.example.drawapp.ViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

const val BITMAP_KEY = "BITMAP_KEY"

val viewModelModule = module {
    viewModel {
        ViewModel()
    }

    single { androidApplication().contentResolver }

    single { ImageSaver(get()) }
}