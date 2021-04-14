package com.athlon.commonlib.http

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface DownloadListener {

    /**
     * 开始下载
     */
    fun start()

    /**
     * 正在下载
     */
    fun loading(progress: Int)

    /**
     * 下载完成
     */
    fun complete(path: String?)

    /**
     * 请求失败
     */
    fun fail(code: Int, message: String?)

    /**
     * 下载过程中失败
     */
    fun loadfail(message: String?)
}