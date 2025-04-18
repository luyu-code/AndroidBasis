package com.xiaolu.basis.network.exception

import com.blankj.utilcode.util.ToastUtils
import com.drake.net.Net
import com.drake.net.NetConfig
import com.drake.net.exception.ConvertException
import com.drake.net.exception.DownloadFileException
import com.drake.net.exception.HttpFailureException
import com.drake.net.exception.NetConnectException
import com.drake.net.exception.NetException
import com.drake.net.exception.NetSocketTimeoutException
import com.drake.net.exception.NoCacheException
import com.drake.net.exception.RequestParamsException
import com.drake.net.exception.ResponseException
import com.drake.net.exception.ServerResponseException
import com.drake.net.exception.URLParseException
import com.drake.net.interfaces.NetErrorHandler
import com.xiaolu.basis.R
import java.net.UnknownHostException

/**
 * <p>
 * 全局网络异常处理
 * </p>
 *
 * @author xiaolu
 * @date Created in 2025/4/18 17:52:41
 */
class NetworkingErrorHandler : NetErrorHandler {
    override fun onError(e: Throwable) {
        val message = when (e) {
            is NoNetworkException -> e.message
            is UnknownHostException -> NetConfig.app.getString(R.string.net_host_error)
            is URLParseException -> NetConfig.app.getString(R.string.net_url_error)
            is NetConnectException -> NetConfig.app.getString(R.string.net_connect_error)
            is NetSocketTimeoutException -> NetConfig.app.getString(
                R.string.net_connect_timeout_error,
                e.message
            )

            is DownloadFileException -> NetConfig.app.getString(R.string.net_download_error)
            is ConvertException -> NetConfig.app.getString(R.string.net_parse_error)
            is RequestParamsException -> NetConfig.app.getString(R.string.net_request_error)
            is ServerResponseException -> NetConfig.app.getString(R.string.net_server_error)
            is NullPointerException -> NetConfig.app.getString(R.string.net_null_error)
            is NoCacheException -> NetConfig.app.getString(R.string.net_no_cache_error)
            is ResponseException -> e.message
            is HttpFailureException -> {
                NetConfig.app.getString(R.string.request_failure)
            }

            is NetException -> NetConfig.app.getString(R.string.net_error)
            else -> NetConfig.app.getString(R.string.net_other_error)
        }
        Net.debug(e)
        ToastUtils.showShort(message)
    }
}