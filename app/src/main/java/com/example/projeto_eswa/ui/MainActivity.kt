package com.example.projeto_eswa.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.projeto_eswa.ui.home.HomeScreen
import com.example.projeto_eswa.utils.FinishAcitivity

class MainActivity : AppCompatActivity(), FinishAcitivity {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            MaterialTheme {
                HomeScreen(
                    finishAcitivity = this,
                    this.packageManager
                )
            }
        }
    }

    override fun finishAcitivity() {
        finish()
    }
}