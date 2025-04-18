package com.xiaolu.basis.view.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.blankj.utilcode.util.ToastUtils
import com.dylanc.viewbinding.base.ActivityBinding
import com.dylanc.viewbinding.base.ActivityBindingDelegate
import com.gyf.immersionbar.ImmersionBar
import com.hjq.language.MultiLanguages
import com.xiaolu.basis.R
import com.xiaolu.basis.constant.Constants
import com.xiaolu.basis.util.MMKVUtils

/**
 * Activity基类
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(),
    ActivityBinding<VB> by ActivityBindingDelegate() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ToastUtils.cancel()
        // 请求窗口特征，移除窗口标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        if (!customerImmersionBar()) {
            setImmersionBar()
        }
        setContentViewWithBinding()
        initComponent()
        loadData()
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v?.windowToken)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标判断是否应该隐藏键盘
     * 点击EditText区域时不隐藏
     */
    private fun isShouldHideKeyboard(v: View?, event: MotionEvent): Boolean {
        if (v != null && v is EditText) {
            val l = intArrayOf(0, 0)
            v.getLocationInWindow(l)
            val left = l[0]
            val top = l[1]
            val right = left + v.width
            val bottom = top + v.height

            return !(event.x > left && event.x < right &&
                    event.y > top && event.y < bottom)
        }
        // 焦点不是EditText时不需要隐藏
        return false
    }

    /**
     * 隐藏软键盘
     */
    private fun hideKeyboard(token: IBinder?) {
        token?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    /**
     * 默认为false不需要自定义沉浸式，用基类的
     */
    open fun customerImmersionBar() = false

    /**
     * 初始化界面控件
     */
    abstract fun initComponent()

    /**
     * 设置沉浸式
     */
    private fun setImmersionBar() {
        ImmersionBar.with(this)
            .statusBarColor(R.color.white)
            .statusBarDarkFont(true)
            .navigationBarColor(R.color.white)
            .navigationBarDarkIcon(true)
            .fullScreen(false)
            .fitsSystemWindows(true)
            .init()
    }

    /**
     * 加载数据
     */
    abstract fun loadData()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onResume() {
        super.onResume()
        ToastUtils.cancel()
        val intentFilter = IntentFilter(Constants.BROADCAST_RECEIVER_TOKEN_ERROR)
        registerReceiver(forceOfflineReceiver, intentFilter, RECEIVER_NOT_EXPORTED)
    }

    override fun onPause() {
        super.onPause()
        ToastUtils.cancel()
        // 取消注册广播接收器
        try {
            unregisterReceiver(forceOfflineReceiver)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private val forceOfflineReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (MMKVUtils.get(Constants.MMKV_KEY_OFFLINE, "").isBlank()) {
//                OfflineDialog(ActivityUtils.getTopActivity()).show()
                //下线弹窗
            }
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        // 绑定语种
        super.attachBaseContext(MultiLanguages.attach(newBase))
    }
}