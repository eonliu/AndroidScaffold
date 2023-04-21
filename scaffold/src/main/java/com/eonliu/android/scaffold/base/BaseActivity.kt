package com.eonliu.android.scaffold.base

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.BarUtils
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel>(@LayoutRes val layoutRes: Int) : AppCompatActivity() {

    protected lateinit var viewModel: VM
    protected lateinit var binding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this

        viewModel = createViewModel()
        viewModel.onParameter(intent.extras)

        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(isInterceptBack()) {
            override fun handleOnBackPressed() {
                onBack()
            }
        })

        initStatusBar()
        onCreated()
    }

    abstract fun onCreated()

    /**
     * 返回
     */
    open fun onBack() = finish()

    /**
     * 是否拦截系统返回事件
     */

    /**
     * 是否拦截系统返回事件
     */
    open fun isInterceptBack() = false

    /**
     * 设置状态栏颜色
     */

    /**
     * 设置状态栏颜色
     */
    open fun setStatusBarColor(@ColorInt color: Int) = BarUtils.setStatusBarColor(this, color)

    /**
     * 状态栏是否透明
     */

    /**
     * 状态栏是否透明
     */
    open fun isTransparentStatusBar() = false

    open fun isStatusBarLightMode() = true

    private fun initStatusBar() {
        if (isTransparentStatusBar()) {
            BarUtils.transparentStatusBar(this)
        }
        if (isStatusBarLightMode()) {
            BarUtils.setStatusBarLightMode(this, true)
        }
    }

    private fun <VM : ViewModel> createViewModel(): VM {
        val vbClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.filterIsInstance<Class<*>>()
        val viewModel = vbClass[1] as Class<VM>
        return ViewModelProvider(this)[viewModel]
    }

}