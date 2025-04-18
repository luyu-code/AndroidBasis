package com.xiaolu.basis.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * <p>
 * 时间工具类
 * </p>
 *
 * @author xiaolu
 * @date Created in 2025/4/18 17:58:23
 */
object DateUtils {
    fun getCurrentTimeByFormat(format: String?): String {
        val df = SimpleDateFormat(format)
        return df.format(Date())
    }

    fun createDateHMS(milliseconds: Long): String {
        return createDateHMS(milliseconds, 0)
    }

    fun createDateHMS(milliseconds: Long, days: Int): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return simpleDateFormat.format(Date(milliseconds - days.toLong() * 86400000L))
    }

    fun createDateHM(milliseconds: Long): String {
        return createDateHM(milliseconds, 0)
    }

    fun createDateHM(milliseconds: Long, days: Int): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
        return simpleDateFormat.format(Date(milliseconds - days.toLong() * 86400000L))
    }

    fun compareDateStrings(
        dateStr1: String,
        dateStr2: String,
        pattern: String = "yyyy/MM/dd"
    ): Int {
        val sdf = SimpleDateFormat(pattern, Locale.getDefault()).apply {
            isLenient = false
        }
        return try {
            // 添加非空断言（parse()在isLenient=false时非空）
            sdf.parse(dateStr1)!!.compareTo(sdf.parse(dateStr2)!!)
        } catch (e: ParseException) {
            throw IllegalArgumentException("Invalid date format. Expected '$pattern'", e)
        }
    }
}