package com.yjq.eyepetizer.util.storage

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

import java.lang.reflect.Field
import java.util.Arrays
import java.util.HashSet

/**
 * 文件： PrefUtil
 * 描述： sharePreference 工具
 * 作者： YangJunQuan   2018-7-31.
 */

object PrefUtil {
    @Synchronized
    fun getStringValue(context: Context, key: String, def: String): String? {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return sharedPreferences.getString(key, def)
    }

    @Synchronized
    fun setStringValue(context: Context, key: String, `val`: String) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val prefEditor = sharedPreferences.edit()
        prefEditor.putString(key, `val`)
        prefEditor.apply()
    }

    @Synchronized
    fun getIntValue(context: Context, key: String, def: Int): Int {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return sharedPreferences.getInt(key, def)
    }

    @Synchronized
    fun setIntValue(context: Context, key: String, `val`: Int) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val prefEditor = sharedPreferences.edit()
        prefEditor.putInt(key, `val`)
        prefEditor.apply()
    }

    @Synchronized
    fun setLongValue(context: Context, key: String, `val`: Long) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val prefEditor = sharedPreferences.edit()
        prefEditor.putLong(key, `val`)
        prefEditor.apply()
    }

    @Synchronized
    fun getLongValue(context: Context, key: String, def: Long): Long {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return sharedPreferences.getLong(key, def)
    }

    @Synchronized
    fun getBooleanValue(context: Context, key: String, def: Boolean): Boolean {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return sharedPreferences.getBoolean(key, def)
    }

    @Synchronized
    fun setBooleanValue(context: Context, key: String, `val`: Boolean) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val prefEditor = sharedPreferences.edit()
        prefEditor.putBoolean(key, `val`)
        prefEditor.apply()
    }

    @Synchronized
    fun clear(context: Context) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val prefEditor = sharedPreferences.edit()
        prefEditor.clear()
        prefEditor.apply()
    }

    @Synchronized
    fun clear(context: Context, constantClass: Class<*>, prefix: String) {
        clear(context, constantClass, prefix, arrayOf())
    }

    @Synchronized
    fun clear(context: Context, constantClass: Class<*>, prefix: String, excepts: Array<String>) {
        val fields = constantClass.declaredFields
        if (fields == null || fields.isEmpty())
            return

        val exceptions = HashSet(Arrays.asList(*excepts))

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val prefEditor = sharedPreferences.edit()
        for (field in fields) {
            if (field.name.startsWith(prefix) && !exceptions.contains(field.name) && field.type == String::class.java) {
                try {
                    prefEditor.remove(field.get(context) as String)
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                }

            }
        }

        prefEditor.apply()
    }


    @Synchronized
    fun clear(context: Context, key: String) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val prefEditor = sharedPreferences.edit()
        prefEditor.remove(key)
        prefEditor.apply()
    }
}

