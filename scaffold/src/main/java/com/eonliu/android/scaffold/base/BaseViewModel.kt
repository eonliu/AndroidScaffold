package com.eonliu.android.scaffold.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.DefaultLifecycleObserver

open class BaseViewModel(application: Application) : AndroidViewModel(application), DefaultLifecycleObserver {

}