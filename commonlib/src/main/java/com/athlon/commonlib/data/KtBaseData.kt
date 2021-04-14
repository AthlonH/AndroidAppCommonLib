package com.athlon.commonlib.data

import com.google.gson.Gson

class KtBaseData(override var dataType: Int = 0): IDataType{
    companion object {
        fun <T : KtBaseData> createData(jsonStr: String): T? {
            var gson = Gson()
            return gson.fromJson(jsonStr, this::class.java) as? T
        }
    }


    fun getJsonStr(): String {
        var gson = Gson()
        return gson.toJson(this)
    }
}