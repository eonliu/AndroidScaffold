package com.eonliu.android.scaffold.util

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

fun Bundle.argumentInt(name: String) = getInt(name, 0)

fun Bundle.argumentString(name: String) = getString(name)

fun Bundle.argumentBoolean(name: String) = getBoolean(name, false)

inline fun <reified T> Bundle.argumentsParcelableExtras(name: String): T =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(name, T::class.java)
    } else {
        getParcelable(name)
    } as T


fun Bundle.argumentsStringArraylist(name: String) = getStringArrayList(name)

inline fun <reified T : Parcelable> Bundle.argumentsParcelableArrayList(name: String): T =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableArrayList(name, T::class.java)
    } else {
        getParcelableArrayList(name)
    } as T

fun Fragment.withArguments(vararg pairs: Pair<String, *>) = apply {
    arguments = bundleOf(*pairs)
}

fun Fragment.argumentInt(name: String) = arguments?.argumentInt(name) ?: 0

fun Fragment.argumentString(name: String) = arguments?.argumentString(name) ?: ""

fun Fragment.argumentBoolean(name: String) = arguments?.argumentBoolean(name) ?: false

inline fun <reified T> Fragment.argumentsParcelableExtras(name: String): T =
    arguments!!.argumentsParcelableExtras(name)

fun Fragment.argumentsStringArraylist(name: String) = arguments?.argumentsStringArraylist(name)

inline fun <reified T : Parcelable> Fragment.argumentsParcelableArrayList(name: String) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arguments!!.getParcelableArrayList(name, T::class.java)
    } else {
        arguments!!.getParcelableArrayList(name)
    }