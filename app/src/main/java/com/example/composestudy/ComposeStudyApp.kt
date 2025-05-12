package com.example.composestudy

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.composestudy.di.ApplicationComponent
import com.example.composestudy.di.DaggerApplicationComponent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ComposeStudyApp : Application() {
    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}

@Composable
fun getApplicationComponent(): ApplicationComponent {
    return (LocalContext.current.applicationContext as ComposeStudyApp).appComponent
}