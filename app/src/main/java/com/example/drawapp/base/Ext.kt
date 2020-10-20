package com.example.drawapp.base

import android.app.Activity
import android.content.pm.PackageManager
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.drawapp.ViewState
const val REQUEST_PERMISSION = 2

fun View.showIf(show: Boolean) {
    if (show) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun Activity.checkPermissionForWriteToStorage(): Boolean {
    val result = ContextCompat.checkSelfPermission(
        this,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    return  result == PackageManager.PERMISSION_GRANTED
}

fun Activity.requestPermissionForWriteToStorage() {
    requestPermissions(
        arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
        REQUEST_PERMISSION
    )
}