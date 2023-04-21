package com.eonliu.android.scaffold.base

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.DefaultLifecycleObserver

open class BaseViewModel(application: Application) : AndroidViewModel(application), DefaultLifecycleObserver {

    fun onParameter(bundle: Bundle?) {

    }

}