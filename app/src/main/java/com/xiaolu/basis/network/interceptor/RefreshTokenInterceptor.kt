package com.xiaolu.basis.network.interceptor

import android.content.Intent
import com.drake.net.NetConfig
import com.drake.net.exception.ResponseException
import com.xiaolu.basis.R
import com.xiaolu.basis.app.AppApplication
import com.xiaolu.basis.constant.Constants
import com.xiaolu.basis.util.MMKVUtils
import okhttp3.Interceptor
import okhttp3.Response


/**
 * 客户端token自动续期示例
 */
class RefreshTokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        return synchronized(RefreshTokenInterceptor::class.java) {
            val tag = request.tag()
            if (tag == Constants.LOGIN_TAG) {
                return@synchronized response
            }
            if (response.code == 401) {
                if (MMKVUtils.get(Constants.MMKV_KEY_OFFLINE, "").isBlank()) {
                    val intent = Intent(Constants.BROADCAST_RECEIVER_TOKEN_ERROR)
                    AppApplication.getInstance().sendBroadcast(intent)//发送全局广播
                }
                throw ResponseException(
                    response,
                    NetConfig.app.getString(R.string.auth_login_status_invalid)
                )
            } else {
                response
            }
        }
    }
}