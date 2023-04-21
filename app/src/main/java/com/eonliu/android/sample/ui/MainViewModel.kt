package com.eonliu.android.sample.ui

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import com.drake.net.Get
import com.drake.net.utils.scopeNet
import com.eonliu.android.scaffold.base.BaseViewModel

class MainViewModel(application: Application) : BaseViewModel(application) {

    var productId: String? = null


    override fun onParameter(bundle: Bundle?) {
        super.onParameter(bundle)
        productId = bundle?.getString("productId", "")
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        request(productId)
    }

    fun request(productId: String?) {
        scopeNet {
            val d = Get<String>("https://github.com/liangjingkanji/Net/").await()


        }.catch {

        }
    }
}