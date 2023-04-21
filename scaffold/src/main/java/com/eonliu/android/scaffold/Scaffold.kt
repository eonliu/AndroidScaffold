package com.eonliu.android.scaffold

import android.app.Application
import com.drake.net.convert.NetConverter
import com.eonliu.android.scaffold.log.DEFAULT_LOG_TAG
import com.eonliu.android.scaffold.log.logConfig
import com.eonliu.android.scaffold.net.Nets
import com.eonliu.android.scaffold.net.SerializationConverter
import com.eonliu.android.scaffold.util.DEFAULT_DATA_STORE_NAME
import com.eonliu.android.scaffold.util.dataStoreName

object Scaffold {
    lateinit var app: Application
    lateinit var config: Config

    fun init(application: Application, config: Config = Config.Builder().create()) {
        this.app = application
        this.config = config
        dataStoreName = config.dataStoreName
        logConfig()
        Nets.init(application, config.baseUrl)
    }

    open class Config protected constructor(builder: Builder) {
        var dataStoreName: String
        var baseUrl: String
        var netReadTimeout: Long = 30
        var netWriteTimeout: Long = 30
        var netDebug: Boolean = true
        var netConverter: NetConverter = SerializationConverter()
        var netHeaders = mutableMapOf<String, String>()
        var logTag: String
        var logSwitch: Boolean = true

        init {
            this.dataStoreName = builder.dataStoreName
            this.baseUrl = builder.baseUrl
            this.netReadTimeout = builder.netReadTimeout
            this.netWriteTimeout = builder.netWriteTimeout
            this.netDebug = builder.netDebug
            this.netConverter = builder.netConverter
            this.netHeaders = builder.netHeaders
            this.logTag = builder.logTag
            this.logSwitch = builder.logSwitch
        }

        class Builder {
            /**
             * data store 名字
             */
            var dataStoreName: String = DEFAULT_DATA_STORE_NAME

            /**
             * 接口地址
             */
            var baseUrl: String = ""

            /**
             * 网络请求read超时时间
             */
            var netReadTimeout: Long = 30

            /**
             * 网络请求write超时时间
             */
            var netWriteTimeout: Long = 30

            /**
             * 网络请求日志开关
             */
            var netDebug: Boolean = true

            /**
             * json转换器
             */
            var netConverter: NetConverter = SerializationConverter()

            /**
             * 请求header
             */
            var netHeaders = mutableMapOf<String, String>()

            /**
             * log tag
             */
            var logTag: String = DEFAULT_LOG_TAG

            /**
             * log 开关(默认开）
             */
            var logSwitch: Boolean = true
            fun setDataStoreName(dataStoreName: String): Builder {
                this.dataStoreName = dataStoreName
                return this
            }

            fun setBaseUrl(baseUrl: String): Builder {
                this.baseUrl = baseUrl
                return this
            }

            fun setLogTag(logTag: String): Builder {
                this.logTag = logTag
                return this
            }

            fun setLogSwitch(logSwitch: Boolean): Builder {
                this.logSwitch = logSwitch
                return this
            }

            fun setNetReadTimeout(netReadTimeout: Long): Builder {
                this.netReadTimeout = netReadTimeout
                return this
            }

            fun setNetWriteTimeout(netWriteTimeout: Long): Builder {
                this.netWriteTimeout = netWriteTimeout
                return this
            }

            fun setNetDebug(netDebug: Boolean): Builder {
                this.netDebug = netDebug
                return this
            }

            fun setNetConverter(netConverter: NetConverter): Builder {
                this.netConverter = netConverter
                return this
            }

            fun setNetHeaders(headers: MutableMap<String, String>): Builder {
                this.netHeaders = headers
                return this
            }

            fun create(): Config {
                return Config(this)
            }
        }
    }
}