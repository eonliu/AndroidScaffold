package com.eonliu.android.scaffold.util

import android.app.Activity
import android.view.View
import android.widget.TextView

inline fun <reified T : View> T.setOnSingleClickListener(crossinline block: T.() -> Unit) {
    var lastClickTime = 0L
    setOnClickListener {
        if (System.currentTimeMillis() - lastClickTime < 500) {
            return@setOnClickListener
        }
        lastClickTime = System.currentTimeMillis()
        (it as T).block()
    }
}

inline fun <reified T : Activity> View.setOnClickStartActivity(vararg pairs: Pair<String, Any?>) {
    setOnSingleClickListener {
        startActivity<T>(*pairs)
    }
}

fun TextView.textString() = text.toString().trim()