package com.eonliu.android.sample

import android.app.Application
import com.eonliu.android.scaffold.Scaffold

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Scaffold.init(this)
    }
}