package com.developersbreach.cutepuppies

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.developersbreach.cutepuppies.ui.theme.PuppyTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PuppyTheme {
                AppNavigation()
            }
        }
    }
}
