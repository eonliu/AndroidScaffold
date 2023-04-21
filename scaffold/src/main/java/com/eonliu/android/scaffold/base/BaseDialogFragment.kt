package com.eonliu.android.scaffold.base

import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eonliu.android.scaffold.R
import java.lang.reflect.ParameterizedType

abstract class BaseDialogFragment<DB : ViewDataBinding, VM : BaseViewModel>(@LayoutRes val layoutRes: Int) : DialogFragment() {
    protected lateinit var viewModel: VM
    protected lateinit var mBinding: DB

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, layoutRes, null, false)
        mBinding.lifecycleOwner = this
        dialog?.setCanceledOnTouchOutside(getCanceled())
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        init()
        return mBinding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel()
        viewModel.onParameter(arguments)
        lifecycle.addObserver(viewModel)
        setStyle(STYLE_NO_TITLE, R.style.BottomDialog)
    }

    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        val params = window?.attributes
        params?.dimAmount = getDimAmount()
        params?.height = getDialogHeight()
        params?.width = getDialogWidth()
        params?.gravity = getDialogGravity()
        window?.attributes = params
    }

    abstract fun init()

    open fun getCanceled() = true

    open fun getDimAmount() = 0.5f

    open fun getDialogHeight() = WindowManager.LayoutParams.WRAP_CONTENT

    open fun getDialogWidth() = WindowManager.LayoutParams.MATCH_PARENT

    open fun getDialogGravity() = Gravity.BOTTOM

    private fun <VM : ViewModel> createViewModel(): VM {
        val vbClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.filterIsInstance<Class<*>>()
        val viewModel = vbClass[1] as Class<VM>
        return ViewModelProvider(this)[viewModel]
    }
}