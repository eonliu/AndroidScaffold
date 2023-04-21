package com.eonliu.android.scaffold.net

import com.drake.net.interceptor.RequestInterceptor
import com.drake.net.request.BaseRequest
import com.eonliu.android.scaffold.Scaffold

class GlobalHeaderInterceptor : RequestInterceptor {
    override fun interceptor(request: BaseRequest) {
        Scaffold.config.netHeaders.keys.forEach { key ->
            request.setHeader(key, Scaffold.config.netHeaders[key] ?: "")
        }

    }
}