package com.eonliu.android.scaffold.base

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 */
class FragmentProxy(
    /**
     * 要操作的Fragment对象
     */
    private var mFragment: Fragment?
) {
    /**
     * 沉浸式实现接口
     */
    private var mFragmentOwner: FragmentOwner? = null

    /**
     * Fragment的view是否已经初始化完成
     */
    private var mIsActivityCreated = false

    /**
     * 懒加载，是否已经在view初始化完成之前调用
     */
    private var mIsLazyAfterView = false

    /**
     * 懒加载，是否已经在view初始化完成之后调用
     */
    private var mIsLazyBeforeView = false

    init {
        if (mFragment is FragmentOwner) {
            mFragmentOwner = mFragment as FragmentOwner?
        } else {
            throw IllegalArgumentException("Fragment请实现ImmersionOwner接口")
        }
    }

    fun onCreate(savedInstanceState: Bundle?) {
        if (mFragment != null && mFragment!!.userVisibleHint) {
            if (!mIsLazyBeforeView) {
                mFragmentOwner!!.onLazyBeforeView()
                mIsLazyBeforeView = true
            }
        }
    }

    fun onActivityCreated(savedInstanceState: Bundle?) {
        mIsActivityCreated = true
        if (mFragment != null && mFragment!!.userVisibleHint) {
            if (!mIsLazyAfterView) {
                mFragmentOwner!!.onLazyAfterView()
                mIsLazyAfterView = true
            }
        }
    }

    fun onResume() {
        if (mFragment != null && mFragment!!.userVisibleHint) {
            mFragmentOwner!!.onVisible()
        }
    }

    fun onPause() {
        if (mFragment != null) {
            mFragmentOwner!!.onInvisible()
        }
    }

    fun onDestroy() {
        mFragment = null
        mFragmentOwner = null
    }

    fun onConfigurationChanged(newConfig: Configuration?) {
        if (mFragment != null && mFragment!!.userVisibleHint) {
            mFragmentOwner!!.onVisible()
        }
    }

    fun onHiddenChanged(hidden: Boolean) {
        if (mFragment != null) {
            mFragment!!.userVisibleHint = !hidden
        }
    }

    /**
     * 是否已经对用户可见
     * Is user visible hint boolean.
     *
     * @return the boolean
     */
    var isUserVisibleHint: Boolean
        get() = if (mFragment != null) {
            mFragment!!.userVisibleHint
        } else {
            false
        }
        set(isVisibleToUser) {
            if (mFragment != null) {
                if (mFragment!!.userVisibleHint) {
                    if (!mIsLazyBeforeView) {
                        mFragmentOwner!!.onLazyBeforeView()
                        mIsLazyBeforeView = true
                    }
                    if (mIsActivityCreated) {
                        if (mFragment!!.userVisibleHint) {
                            if (!mIsLazyAfterView) {
                                mFragmentOwner!!.onLazyAfterView()
                                mIsLazyAfterView = true
                            }
                            mFragmentOwner!!.onVisible()
                        }
                    }
                } else {
                    if (mIsActivityCreated) {
                        mFragmentOwner!!.onInvisible()
                    }
                }
            }
        }
}