package com.example.projeto_eswa.utils

import android.content.Context
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class PermissionRequester(
    private val context: Context,
    private val permission: String
) {
    fun requestPermission(onResult: (Boolean) -> Unit) {
        val launcher = (context as AppCompatActivity).registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            onResult(isGranted)
        }
        launcher.launch(permission)
    }
}