package com.athlon.commonlib.http

import okhttp3.*
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class HttpClient(val url: String, val mediaTypeStr: String = TYPE_JPEG, val charsetStr: String = CHARSET_UTF8) {
    companion object {
        /**
         * 纯文本格式
         */
        val TYPE_TEXT = "text/pain"

        /**
         * HTML格式
         */
        val TYPE_HTML = "text/pain"

        /**
         * jpg图片格式
         */
        val TYPE_JPEG = "image/jpeg"

        /**
         * png图片格式
         */
        val TYPE_PNG = "image/png"

        /**
         * JSON数据格式
         */
        val TYPE_JSON = "application/json"

        /**
         * form表单encType属性的默认格式，表单数据将以key/value的形式发送到服务端, 使用FromBody
         */
        val TYPE_FROM_URLENCODED = "application/x-www-form-urlencoded"

        /**
         * 二进制流数据（如常见的文件下载）
         */
        val TYPE_STREAM = "application/octet-stream"

        /**
         * 表单上传文件的格式
         */
        val TYPE_MULTIPART = "multipart/form-data"

        val CHARSET_UTF8 = "utf-8"
    }

    private var clientBuilder: OkHttpClient.Builder

    private var requestBuilder: Request.Builder

    private var requestBody: RequestBody ?= null

    init {
        clientBuilder = OkHttpClient.Builder()
        clientBuilder.connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)

        requestBuilder = Request.Builder()
        requestBuilder.url(url)
    }

    fun addHead(key: String, value: String) {
        requestBuilder.addHeader(key, value)
    }

    fun addInterceptor(interceptor: Interceptor?) {
        clientBuilder.addInterceptor(interceptor)
    }

    private fun requestBody(body: String): HttpClient {
        val mediaType = MediaType.parse("$mediaTypeStr; charset=$charsetStr")
        requestBody = RequestBody.create(mediaType, body)
        return this
    }

    fun post(httpResponse: HttpResponse, body: String? = null) {
        if (body == null) {
            requestBody(JSONObject().toString())
        } else {
            requestBody(body)
        }
        var request = requestBuilder.post(requestBody).build()
        var call = clientBuilder.build().newCall(request)
        call.enqueue(httpResponse)
    }

    fun get(httpResponse: HttpResponse) {
        var request = requestBuilder.get().build()
        var call = clientBuilder.build().newCall(request)
        call.enqueue(httpResponse)
    }



    fun delete(httpResponse: HttpResponse, body: String? = null) {
        if (body != null) {
            requestBody(body)
            requestBuilder.delete(requestBody)
        } else {
            requestBuilder.delete()
        }
        var request = requestBuilder.build()
        var call = clientBuilder.build().newCall(request)
        call.enqueue(httpResponse)
    }


    fun download(response: DownloadResponse) {
        addHead("RANGE", "bytes=" + response.startsPoint.toString() + "-")
        var request = requestBuilder.get().build()
        var call = clientBuilder.build().newCall(request)
        call.enqueue(response)
    }
}