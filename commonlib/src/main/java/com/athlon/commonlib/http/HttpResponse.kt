package com.athlon.commonlib.http

import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

abstract class HttpResponse: Callback {

    override fun onFailure(call: Call, e: IOException) {
        onFailed()
    }

    override fun onResponse(call: Call, response: Response) {
        val code = response.code()
        var dataStr = response.body()?.string()
        if (interruptResponse(call, code, dataStr)) {
            return
        }
        onResponse(code, dataStr)
    }


    fun interruptResponse(call: Call, code: Int, dataStr: String?): Boolean{
        //需要中断response, 则覆盖改方法，返回true
        return false
    }





    abstract fun onFailed()
    abstract fun onResponse(code: Int, dataStr: String?)
}