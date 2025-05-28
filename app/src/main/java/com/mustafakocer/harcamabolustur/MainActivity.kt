package com.mustafakocer.harcamabolustur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mustafakocer.harcamabolustur.ui.theme.HarcamaBolusturTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HarcamaBolusturTheme {
            }
        }
    }
}

