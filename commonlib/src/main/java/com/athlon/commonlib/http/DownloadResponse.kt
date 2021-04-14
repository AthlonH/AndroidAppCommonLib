package com.athlon.commonlib.http

import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.*

class DownloadResponse(file: File, downloadListener: DownloadListener) : Callback {

    var downloadListener: DownloadListener
    var downloadFile: File
    var tmpFile: File
    var startsPoint: Long

    override fun onFailure(call: Call, e: IOException) {
        downloadListener.fail(ResponseCode.ERROR_IO_EXCEPTION, e.message)
    }

    @Throws(IOException::class)
    override fun onResponse(call: Call, response: Response) {
        val code = response.code()
        if (code > 300) {
            downloadListener.fail(code, response.body().toString())
        } else {
            val length = response.body()!!.contentLength()
            //            if (length == 0){
//                // 说明文件已经下载完，直接跳转安装就好
//                downloadListener.complete(String.valueOf(downloadFile.getAbsoluteFile()));
//                return;
//            }
            downloadListener.start()
            // 保存文件到本地
            var `is`: InputStream? = null
            var randomAccessFile: RandomAccessFile? = null
            var bis: BufferedInputStream? = null
            val buff = ByteArray(2048)
            var len = 0
            var bytesReaded = startsPoint
            try {
                `is` = response.body()!!.byteStream()
                bis = BufferedInputStream(`is`)

                // 随机访问文件，可以指定断点续传的起始位置
                randomAccessFile = RandomAccessFile(tmpFile, "rwd")
                randomAccessFile.seek(startsPoint)
                while (bis.read(buff).also { len = it } != -1) {
                    randomAccessFile.write(buff, 0, len)
                    bytesReaded += len.toLong()
                    downloadListener.loading((bytesReaded * 100 / (length + startsPoint)).toInt())
                }

                // 下载完成
                tmpFile.renameTo(downloadFile)
                downloadListener.complete(downloadFile.absoluteFile.toString())
            } catch (e: Exception) {
                e.printStackTrace()
                downloadListener.loadfail(e.message)
            } finally {
                try {
                    `is`?.close()
                    bis?.close()
                    randomAccessFile?.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    init {
        this.downloadListener = downloadListener
        downloadFile = file
        tmpFile = File(file.absolutePath + ".tmp")
        startsPoint = tmpFile.length() - 2048
        if (startsPoint < 0) {
            startsPoint = 0
        }
        tmpFile.parentFile.mkdirs()
    }
}