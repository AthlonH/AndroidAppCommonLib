package com.athlon.commonlib.http

object ResponseCode {

    const val EMPTY_DATA = -1
    const val SUCCESS_CODE = 0
    const val SUCCESS = 200

    const val ERROR_IO_EXCEPTION = 11001


    const val ERROR_TOKEN = 1009 //message: 'wrong token'

    const val ERROR_TOKEN_EXPIRE = 1010 //message: 'token expire'


    const val ERROR_UNKNOWN = 50000 //未知错误


    const val ERROR_JSONEXCEPTION = 80001


    const val DUPLICATE_CONNECT = 80002
}