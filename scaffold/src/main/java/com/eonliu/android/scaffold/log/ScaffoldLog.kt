package com.eonliu.android.scaffold.log

import com.blankj.utilcode.util.LogUtils

const val DEFAULT_LOG_TAG = "Scaffold_TAG"

fun logConfig(): LogUtils.Config {
    return LogUtils.getConfig()
        .setGlobalTag(DEFAULT_LOG_TAG)
        .setLogSwitch(true)
}

fun logV(vararg content: Any) = LogUtils.v(content)

fun logD(vararg content: Any) = LogUtils.d(content)

fun logI(vararg content: Any) = LogUtils.i(content)

fun logW(vararg content: Any) = LogUtils.w(content)

fun logE(vararg content: Any) = LogUtils.e(content)

fun logA(vararg content: Any) = LogUtils.a(content)

fun logFile(content: Any) = LogUtils.file(content)

fun logJson(content: Any) = LogUtils.json(content)

fun logXml(content: String) = LogUtils.xml(content)
