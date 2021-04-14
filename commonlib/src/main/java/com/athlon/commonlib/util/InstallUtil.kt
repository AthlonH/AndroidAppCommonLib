package com.athlon.commonlib.util

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

class InstallUtil {


    fun bgInstallApk(apkAbsolutePath: String): String? {
        val args =
            arrayOf("pm", "install", "-r", apkAbsolutePath)
        var result: String? = ""
        val processBuilder = ProcessBuilder(*args)
        var process: Process? = null
        var errIs: InputStream? = null
        var inIs: InputStream? = null
        try {
            val baos = ByteArrayOutputStream()
            var read = -1
            process = processBuilder.start()
            errIs = process.errorStream
            while (errIs.read().also({ read = it }) != -1) {
                baos.write(read)
            }
            baos.write("/n".toByteArray())
            inIs = process.inputStream
            while (inIs.read().also({ read = it }) != -1) {
                baos.write(read)
            }
            val data: ByteArray = baos.toByteArray()
            result = String(data)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                if (errIs != null) {
                    errIs.close()
                }
                if (inIs != null) {
                    inIs.close()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            process?.destroy()
        }
        return result
    }

}