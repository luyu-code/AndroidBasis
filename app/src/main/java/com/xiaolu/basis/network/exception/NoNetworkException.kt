package com.xiaolu.basis.network.exception

import java.io.IOException

/**
 * <p>
 * 无网络连接异常类
 * </p>
 *
 * @author xiaolu
 * @date Created in 2025/4/18 16:20:03
 */
class NoNetworkException(
    message: String? = null,
) : IOException( message)
