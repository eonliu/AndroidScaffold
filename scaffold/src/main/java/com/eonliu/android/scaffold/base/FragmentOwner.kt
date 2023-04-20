package com.eonliu.android.scaffold.base

/**
 */
interface FragmentOwner {
    /**
     * 懒加载，在view初始化完成之前执行
     */
    fun onLazyBeforeView()

    /**
     * 懒加载，在view初始化完成之后执行
     */
    fun onLazyAfterView()

    /**
     * 用户可见时候调用
     */
    fun onVisible()

    /**
     * 用户不可见时候调用
     */
    fun onInvisible()
}