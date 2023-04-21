package com.eonliu.android.scaffold.net

import android.app.Application
import com.drake.net.NetConfig
import com.drake.net.okhttp.setConverter
import com.drake.net.okhttp.setRequestInterceptor
import com.eonliu.android.scaffold.Scaffold
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor

object Nets {

    fun init(application: Application, baseUrl: String) {
        NetConfig.initialize(baseUrl, application) {
            setRequestInterceptor(GlobalHeaderInterceptor())
            addInterceptor(OkHttpProfilerInterceptor())
            setConverter(Scaffold.config.netConverter)
        }
    }

}