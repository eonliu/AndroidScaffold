package com.eonliu.android.scaffold

import android.app.Application
import com.eonliu.android.scaffold.log.logConfig

object Scaffold {
    lateinit var app: Application

    fun init(application: Application) {
        this.app = application
        logConfig()
    }
}