package com.xiaolu.basis.util

import android.os.Parcelable
import com.tencent.mmkv.MMKV
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

/**
 * <p>
 * MMKV工具类
 * </p>
 *
 * @author xiaolu
 * @date Created in 2025/4/18 16:30:40
 */
object MMKVUtils {
    var mmkv: MMKV? = MMKV.defaultMMKV()

    inline fun <reified T> set(key: String, value: T) {
        when (value) {
            is String -> mmkv?.encode(key, value)
            is Float -> mmkv?.encode(key, value)
            is Boolean -> mmkv?.encode(key, value)
            is Int -> mmkv?.encode(key, value)
            is Long -> mmkv?.encode(key, value)
            is Double -> mmkv?.encode(key, value)
            is ByteArray -> mmkv?.encode(key, value)
            is Parcelable -> mmkv?.encode(key, value)
            else -> {
                try {
                    val serializer = serializer<T>()
                    val jsonString = Json.encodeToString(serializer, value)
                    mmkv?.encode(key, jsonString)
                } catch (e: Exception) {
                    // 处理序列化异常
                    e.printStackTrace()
                }
            }
        }
    }

    inline fun <reified T> get(key: String, defaultValue: T): T {
        if (mmkv?.containsKey(key) == false) {
            return defaultValue
        }
        return when (defaultValue) {
            is String -> mmkv?.decodeString(key, defaultValue) as T
            is Float -> mmkv?.decodeFloat(key, defaultValue) as T
            is Boolean -> mmkv?.decodeBool(key, defaultValue) as T
            is Int -> mmkv?.decodeInt(key, defaultValue) as T
            is Long -> mmkv?.decodeLong(key, defaultValue) as T
            is Double -> mmkv?.decodeDouble(key, defaultValue) as T
            is ByteArray -> mmkv?.decodeBytes(key, defaultValue) as T
            is Parcelable -> mmkv?.decodeParcelable(key, defaultValue.javaClass) as T
            else -> {
                try {
                    val jsonString = mmkv?.decodeString(key) ?: return defaultValue
                    val serializer = serializer<T>()
                    return Json.decodeFromString(serializer, jsonString)
                } catch (e: SerializationException) {
                    // 处理反序列化异常
                    e.printStackTrace()
                    defaultValue
                }
            }
        }
    }

    fun <T : Parcelable> get(key: String, tClass: Class<T>): T? {
        if (mmkv?.containsKey(key) == false) {
            return null
        }
        return mmkv?.decodeParcelable(key, tClass)
    }

    fun removeKey(key: String) {
        mmkv?.removeValueForKey(key)
    }

    fun clearAll() {
        mmkv?.clearAll()
    }

}