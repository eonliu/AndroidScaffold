package com.eonliu.android.scaffold.log

import com.blankj.utilcode.util.LogUtils

fun logV(vararg content: Any) = LogUtils.v(content)
fun logV(tag: String, vararg content: Any) = LogUtils.v(tag, content)

fun logD(vararg content: Any) = LogUtils.d(content)
fun logD(tag: String, vararg content: Any) = LogUtils.d(tag, content)

fun logI(vararg content: Any) = LogUtils.i(content)
fun logI(tag: String, vararg content: Any) = LogUtils.i(tag, content)

fun logW(vararg content: Any) = LogUtils.w(content)
fun logW(tag: String, vararg content: Any) = LogUtils.w(tag, content)

fun logE(vararg content: Any) = LogUtils.e(content)
fun logE(tag: String, vararg content: Any) = LogUtils.e(tag, content)

fun logA(vararg content: Any) = LogUtils.a(content)
fun logA(tag: String, vararg content: Any) = LogUtils.a(tag, content)

fun logFile(content: Any) = LogUtils.file(content)
fun logFile(tag: String, content: Any) = LogUtils.file(tag, content)

fun logJson(content: Any) = LogUtils.json(content)
fun logJson(tag: String, content: Any) = LogUtils.json(tag, content)

fun logXml(content: String) = LogUtils.xml(content)
fun logXml(tag: String, content: String) = LogUtils.xml(tag, content)