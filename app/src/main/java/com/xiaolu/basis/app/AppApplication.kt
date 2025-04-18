package com.xiaolu.basis.app

import android.app.Activity
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.webkit.WebView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ToastUtils
import com.drake.net.BuildConfig
import com.drake.net.NetConfig
import com.drake.net.interceptor.LogRecordInterceptor
import com.drake.net.okhttp.setConverter
import com.drake.net.okhttp.setDebug
import com.drake.net.okhttp.setDialogFactory
import com.drake.net.okhttp.setErrorHandler
import com.drake.net.okhttp.setRequestInterceptor
import com.drake.net.okhttp.trustSSLCertificate
import com.drake.tooltip.dialog.BubbleDialog
import com.hjq.language.MultiLanguages
import com.tencent.mmkv.MMKV
import com.xiaolu.basis.R
import com.xiaolu.basis.network.api.Api
import com.xiaolu.basis.network.convert.SerializationConverter
import com.xiaolu.basis.network.exception.NetworkingErrorHandler
import com.xiaolu.basis.network.interceptor.GlobalHeaderInterceptor
import com.xiaolu.basis.network.interceptor.NetworkInterceptor
import com.xiaolu.basis.network.interceptor.RefreshTokenInterceptor
import okhttp3.Cache
import java.util.concurrent.TimeUnit

/**
 * <p>
 * App应用主入口
 * </p>
 *
 * @author xiaolu
 * @date Created in 2025/4/18 17:45:33
 */
class AppApplication : Application() {

    companion object {

        private lateinit var instance: AppApplication

        fun getInstance(): AppApplication {
            return instance
        }

    }

    override fun onCreate() {
        super.onCreate()
        // 清理 WebView 缓存
        WebView(applicationContext).apply {
            clearCache(true)
            clearFormData()
            clearHistory()
            clearSslPreferences()
            destroy()
        }
        instance = this
        // 初始化语种切换框架
        MultiLanguages.init(this)
        // 初始化MMKV
        MMKV.initialize(this)
        //网络状态变化监听
        networkListener()
        //初始化网络配置
        NetConfig.initialize(Api.BASE_URL, this) {
            trustSSLCertificate() // 信任所有证书
            // Net支持Http缓存协议和强制缓存模式
            // 当超过maxSize最大值会根据最近最少使用算法清除缓存来限制缓存大小 设置为128MB
            cache(Cache(cacheDir, 1024 * 1024 * 128))
            // 超时配置, 默认是30秒, 设置太长时间会导致用户等待过久
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            setDebug(BuildConfig.DEBUG)
            setRequestInterceptor(GlobalHeaderInterceptor())
            addInterceptor(NetworkInterceptor(this@AppApplication))
            addInterceptor(RefreshTokenInterceptor())
            addInterceptor(LogRecordInterceptor(BuildConfig.DEBUG))
            setErrorHandler(NetworkingErrorHandler())
            setConverter(SerializationConverter()) // 设置自定义转换器
            // 自定义全局加载对话框
            setDialogFactory {
                BubbleDialog(it, getString(R.string.loading))
            }
        }
    }

    private fun networkListener() {
        // 注册网络状态变化监听
        ContextCompat.getSystemService<ConnectivityManager?>(this, ConnectivityManager::class.java)
            ?.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
                override fun onLost(network: Network) {
                    val topActivity: Activity? = ActivityUtils.getTopActivity()
                    if (topActivity !is LifecycleOwner) {
                        return
                    }
                    val lifecycleOwner: LifecycleOwner = topActivity
                    if (lifecycleOwner.lifecycle.currentState != Lifecycle.State.RESUMED) {
                        return
                    }
                    ToastUtils.showShort(R.string.network_unavailable)
                }
            })
    }

    override fun attachBaseContext(base: Context?) {
        //绑定语种
        super.attachBaseContext(MultiLanguages.attach(base))
    }
}