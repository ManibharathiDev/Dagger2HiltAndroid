package com.example.didaggerhilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * HiltAndroidApp annotations is basement for Dagger2 Hilt. It is very important.
 * Once added, Rebuild your project and see generated files
 *
 */
@HiltAndroidApp
class MyApplication : Application() {
}