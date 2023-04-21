package com.eonliu.android.scaffold.util

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


fun TextView.textString() = text.toString().trim()