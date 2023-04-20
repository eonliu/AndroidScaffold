package com.eonliu.android.sample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eonliu.android.sample.R
import com.eonliu.android.scaffold.log.logD

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logD("1231231")
    }
}