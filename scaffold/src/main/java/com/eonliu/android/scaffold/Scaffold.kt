package com.eonliu.android.scaffold

import android.app.Application
import com.eonliu.android.scaffold.log.logConfig
import com.eonliu.android.scaffold.util.DEFAULT_DATA_STORE_NAME

object Scaffold {
    lateinit var app: Application

    fun init(application: Application, dataStoreName: String = DEFAULT_DATA_STORE_NAME) {
        this.app = application
        logConfig()
    }
}