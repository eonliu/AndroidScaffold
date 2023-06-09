package com.eonliu.android.scaffold.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import java.util.*

internal val activityCache = LinkedList<AppCompatActivity>()

val activityList: List<AppCompatActivity> get() = activityCache.toList()

val topActivity: AppCompatActivity get() = activityCache.last()

fun startActivity(intent: Intent) = topActivity.startActivity(intent)

inline fun <reified T : Activity> View.startActivity(
    vararg pairs: Pair<String, Any?>, crossinline block: Intent.() -> Unit = {}
) = context.startActivity(context.intentOf<T>(*pairs).apply(block))

inline fun <reified T : Activity> Fragment.startActivity(
    vararg pairs: Pair<String, Any?>, crossinline block: Intent.() -> Unit = {}
) = requireActivity().startActivity(requireActivity().intentOf<T>(*pairs).apply(block))

inline fun <reified T : Activity> startActivity(
    vararg pairs: Pair<String, Any?>, crossinline block: Intent.() -> Unit = {}
) = topActivity.startActivity<T>(pairs = pairs, block = block)

inline fun <reified T : Activity> Context.startActivity(
    vararg pairs: Pair<String, Any?>, crossinline block: Intent.() -> Unit = {}
) = startActivity(intentOf<T>(*pairs).apply(block))

inline fun <reified T> Context.intentOf(vararg pairs: Pair<String, *>): Intent =
    intentOf<T>(bundleOf(*pairs))

inline fun <reified T> Context.intentOf(bundle: Bundle): Intent =
    Intent(this, T::class.java).putExtras(bundle)

fun Activity.intentIntExtras(name: String, default: Int = 0) = intent.getIntExtra(name, default)

fun Activity.intentStringExtras(name: String): String? = intent.getStringExtra(name)

fun Activity.intentBooleanExtras(name: String) = intent.getBooleanExtra(name, false)

inline fun <reified T> Activity.intentParcelableExtras(name: String): T? =
    intent.intentParcelableExtras(name)

inline fun <reified T : Parcelable> Activity.intentParcelableArrayListExtras(name: String): ArrayList<T>? =
    intent.intentParcelableArrayListExtras(name)


inline fun <reified T> Intent.intentParcelableExtras(name: String): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(name, T::class.java)
    } else {
        getParcelableExtra(name) as T?
    }

inline fun <reified T : Parcelable> Intent.intentParcelableArrayListExtras(name: String) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableArrayListExtra(name, T::class.java)
    } else {
        getParcelableArrayListExtra(name)
    }

fun finishAllActivity() {
    activityCache.forEach { it.finish() }
}

fun AppCompatActivity.onBackActivity() {
    activityCache.remove(this)
    onBackPressedDispatcher.onBackPressed()
}

inline val Activity.activity: Activity get() = this

val Context.activity: Activity?
    get() {
        var context: Context? = this
        while (context is ContextWrapper) {
            if (context is Activity) {
                return context
            }
            context = context.baseContext
        }
        return null
    }

val Context.screenWidthDp: Float
    get() {
        val displayMetrics = resources.displayMetrics
        return displayMetrics.widthPixels / displayMetrics.density
    }
