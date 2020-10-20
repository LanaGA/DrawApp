package com.example.drawapp.koin

import com.example.drawapp.savecomponent.ImageSaver
import com.example.drawapp.ViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        ViewModel()
    }

    single { androidApplication().contentResolver }

    single { ImageSaver(get()) }
}