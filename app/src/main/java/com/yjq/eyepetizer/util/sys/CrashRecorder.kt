package com.yjq.eyepetizer.util.sys

/**
 * Created by f on 2016-11-28.
 *
 *
 * 这个类的作用是记录程序崩溃时的日志于sd卡的multiscreen文件夹下
 * 目前仅限以测试时使用，正式版时不用，因为会产生永久文件及友盟有记录。
 *
 *
 * 这个类的作用是记录程序崩溃时的日志于sd卡的multiscreen文件夹下
 * 目前仅限以测试时使用，正式版时不用，因为会产生永久文件及友盟有记录。
 *
 *
 * 这个类的作用是记录程序崩溃时的日志于sd卡的multiscreen文件夹下
 * 目前仅限以测试时使用，正式版时不用，因为会产生永久文件及友盟有记录。
 */


/**
 * 这个类的作用是记录程序崩溃时的日志于sd卡的multiscreen文件夹下
 * 目前仅限以测试时使用，正式版时不用，因为会产生永久文件及友盟有记录。
 */

import android.os.Environment
import android.text.format.DateFormat
import android.util.Log
import java.io.*

class CrashRecorder : Thread.UncaughtExceptionHandler {

    companion object {
        const val TAG = "CrashRecorder"
        const val NAME = "CRASH"
    }

    private val defaultUEH: Thread.UncaughtExceptionHandler? = Thread.getDefaultUncaughtExceptionHandler()

    override fun uncaughtException(thread: Thread, ex: Throwable) {

        val result = StringWriter()
        val printWriter = PrintWriter(result)
        ex.printStackTrace(printWriter)
        val stacktrace = result.toString()
        printWriter.close()

        Log.e(TAG, stacktrace)
        if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) {
            defaultUEH!!.uncaughtException(thread, ex)
            return  // We can't save the log if SD card is unavailable
        }
        val sdcardPath = Environment.getExternalStorageDirectory().path + "/" + NAME
        val dir = File(sdcardPath)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        writeLog(stacktrace, sdcardPath + "/crash")

        defaultUEH?.uncaughtException(thread, ex)

    }

    private fun writeLog(log: String, name: String) {
        val timestamp = DateFormat.format("yyyyMMdd_kkmmss", System.currentTimeMillis())
        val filename = name + "_" + timestamp + ".log"

        try {
            val stream = FileOutputStream(filename)
            val output = OutputStreamWriter(stream)
            val bw = BufferedWriter(output)

            bw.write(log)
            bw.newLine()

            bw.close()
            output.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

//    private fun writeLogcat(name: String) {
//        val timestamp = DateFormat.format("yyyyMMdd_kkmmss", System.currentTimeMillis())
//        val filename = name + "_" + timestamp + ".log"
//        val args = arrayOf("logcat", "-v", "time", "-d")
//
//        try {
//            val process = Runtime.getRuntime().exec(args)
//            val input = InputStreamReader(
//                    process.inputStream)
//            val output = OutputStreamWriter(
//                    FileOutputStream(filename))
//            val br = BufferedReader(input)
//            val bw = BufferedWriter(output)
//            var line: String?
//
//            while ((line = br.readLine() != null) {
//                        bw.write(line)
//                        bw.newLine()
//                    }
//
//                    bw.close()
//                    output.close()
//                    br.close()
//                    input.close()
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//
//    }


}

