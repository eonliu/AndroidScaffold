package com.eonliu.android.scaffold.base

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel>(@LayoutRes val layoutRes: Int) : Fragment(), FragmentOwner {

    protected lateinit var viewModel: VM
    protected lateinit var binding: DB

    private val mFragmentProxy = FragmentProxy(this)
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        mFragmentProxy.isUserVisibleHint = isVisibleToUser
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel()
        viewModel.onParameter(arguments)
        lifecycle.addObserver(viewModel)
        mFragmentProxy.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.lifecycleOwner = this
        init()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mFragmentProxy.onActivityCreated(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        mFragmentProxy.onResume()
    }

    override fun onPause() {
        super.onPause()
        mFragmentProxy.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mFragmentProxy.onDestroy()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        mFragmentProxy.onHiddenChanged(hidden)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        mFragmentProxy.onConfigurationChanged(newConfig)
    }

    open fun init() {}

    /**
     * 懒加载，在view初始化完成之前执行
     */
    override fun onLazyBeforeView() {}

    /**
     * 懒加载，在view初始化完成之后执行
     */
    override fun onLazyAfterView() {}

    /**
     * Fragment用户可见时候调用
     */
    override fun onVisible() {}

    /**
     * Fragment用户不可见时候调用
     */
    override fun onInvisible() {}

    private fun <VM : ViewModel> createViewModel(): VM {
        val vbClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.filterIsInstance<Class<*>>()
        val viewModel = vbClass[1] as Class<VM>
        return ViewModelProvider(this)[viewModel]
    }
}