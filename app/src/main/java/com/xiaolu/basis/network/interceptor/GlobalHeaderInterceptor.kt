package com.xiaolu.basis.network.interceptor

import com.drake.net.interceptor.RequestInterceptor
import com.drake.net.request.BaseRequest
import com.xiaolu.basis.constant.Constants
import com.xiaolu.basis.util.MMKVUtils

/**
 * 添加全局请求头
 */
class GlobalHeaderInterceptor : RequestInterceptor {
    override fun interceptor(request: BaseRequest) {
        val token = MMKVUtils.get(Constants.MMKV_KEY_USER_TOKEN, "")
        if (token.isNotBlank()) {
            request.setHeader("Authorization", "Bearer $token")
        }
    }
}