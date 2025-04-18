package com.xiaolu.basis.network.response

import kotlinx.serialization.Serializable

/**
 * <p>
 * Response基类,看公司的后端返回数据结构来定义
 * </p>
 *
 * @author xiaolu
 * @date Created in 2025/4/18 11:30:01
 */
@Serializable
open class ApiResponse<T> {
    val code: Int = 0
    val message: String = ""
    val result: T? = null
    val success: Boolean = false
    val timestamp: Long = System.currentTimeMillis()
}