package com.eonliu.android.sample.ui

import com.drake.brv.utils.linear
import com.drake.brv.utils.setup
import com.eonliu.android.sample.R
import com.eonliu.android.sample.databinding.ActivityMainBinding
import com.eonliu.android.scaffold.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
    override fun onCreated() {
        binding.data = "测试data binding"
        binding.recyclerView.linear().setup {
            addType<String>(R.layout.item_asdasd)

            models = listOf("1w312", "1w312", "1w312", "1w312", "1w312", "1w312", "1w312", "1w312", "1w312")
        }
    }
}