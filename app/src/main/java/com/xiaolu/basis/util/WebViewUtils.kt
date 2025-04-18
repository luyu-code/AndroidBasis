package com.xiaolu.basis.util

import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.xiaolu.basis.app.AppApplication
import com.xiaolu.basis.constant.Constants

/**
 * <p>
 * WebView工具类
 * </p>
 *
 * @author xiaolu
 * @date Created in 2025/4/18 15:06:18
 */
object WebViewUtils {
    /**
     * 初始化 WebView 的常用设置
     *
     * @param webView WebView 实例
     */
    fun initWebViewSettings(webView: WebView) {
        val webSettings = webView.settings

        // 禁止长按事件触发默认的菜单（防止用户长按图片等触发默认操作）
        webView.setOnLongClickListener { true }

        // 启用 JavaScript 支持
        webSettings.javaScriptEnabled = true
        // 允许网页定位
        webSettings.setGeolocationEnabled(true)
        // 启用 DOM storage API
        webSettings.domStorageEnabled = true

        // 允许访问文件
        webSettings.allowFileAccess = true

        // 允许访问内容访问
        webSettings.allowContentAccess = true

        // 允许 JavaScript 自动打开窗口
        webSettings.javaScriptCanOpenWindowsAutomatically = true

        // 禁用缓存
         webSettings.cacheMode = WebSettings.LOAD_NO_CACHE

        // 支持缩放（适应屏幕）
        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false
        // 加快网页加载完成的速度，等页面完成再加载图片
        webSettings.loadsImagesAutomatically = true
        // 解决 Android 5.0 上 WebView 默认不允许加载 Http 与 Https 混合内容
        webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        // 使用宽视图模式
        webSettings.useWideViewPort = true

        // 开启概览模式
        webSettings.loadWithOverviewMode = true
        // 设置 WebView 客户端
        webView.webViewClient = object : WebViewClient() {
            // 在 API 24 及以上推荐使用此方法处理页面加载
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                val url = request.url.toString()
                val scheme: String = Uri.parse(url).scheme ?: return false
                when (scheme) {
                    "http", "https" -> view.loadUrl(url)
                }
                return true
            }
        }
    }

    /**
     * 加载指定 URL 的网页
     * @param url 要加载的网页 URL
     */
    fun loadUrl(webView: WebView, url: String) {
        webView.loadUrl(url)
    }

    /**
     * 显示 WebView 加载动画
     *
     * @param webView WebView 实例
     */
    fun showLoading(webView: WebView) {
        webView.visibility = View.VISIBLE
        webView.webViewClient = object : WebViewClient() {
            // 在 API 24 及以上推荐使用此方法处理页面加载完成事件
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                // 页面加载完成时隐藏加载动画
                view.evaluateJavascript("hideLoading()", null)
            }
        }
        webView.loadUrl("file:///android_asset/loading.html")
    }

    /**
     * 隐藏 WebView 加载动画
     *
     * @param webView WebView 实例
     */
    fun hideLoading(webView: WebView) {
        webView.evaluateJavascript("hideLoading()", null)
    }

    /**
     * token失效下线，弹窗提示
     */
    fun handlerOffLine() {
        if (MMKVUtils.get(Constants.MMKV_KEY_OFFLINE, "").isBlank()) {
            val intent = Intent(Constants.BROADCAST_RECEIVER_TOKEN_ERROR)
            AppApplication.getInstance().sendBroadcast(intent)//发送全局广播
        }
    }

    /**
     * 清理缓存
     */
    fun clearCache(webView: WebView) {
        with(webView) {
            (webView.parent as ViewGroup).removeView(webView)
            // 停止加载网页
            stopLoading()
            // 清除历史记录
            clearHistory()
            clearCache(true)
            // 销毁此的WebView的内部状态
            destroy()
        }
    }
}