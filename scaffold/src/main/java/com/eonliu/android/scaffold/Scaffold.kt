package com.eonliu.android.scaffold

import android.app.Application

object Scaffold {
    lateinit var app: Application

    fun init(application: Application) {
        this.app = application
    }
}