package com.athlon.commonlib.ui

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import com.athlon.commonlib.R

object CustomToast {

    fun show(context: Context, message: String) {
        //加载Toast布局
        val toastRoot: View = LayoutInflater.from(context).inflate(R.layout.toast_custom, null)
        //初始化布局控件
        val textView = toastRoot.findViewById(R.id.message) as TextView
        //为控件设置属性
        textView.text = message
        //Toast的初始化
        val toastStart = Toast(context)
        //获取屏幕高度
        val wm =
                context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val height = wm.defaultDisplay.height
        //Toast的Y坐标是屏幕高度的1/3，不会出现不适配的问题
        toastStart.setGravity(Gravity.TOP, 0, height / 3)
        toastStart.duration = Toast.LENGTH_SHORT
        toastStart.view = toastRoot
        toastStart.show()
    }


    fun showErrorDialog(context: Context, title: String, message: String) {
        //加载Toast布局
        val toastRoot: View = LayoutInflater.from(context).inflate(R.layout.toast_error_content_dialog, null)
        //初始化布局控件
        val textView = toastRoot.findViewById(R.id.msgTv) as TextView
        val titleTv = toastRoot.findViewById(R.id.titleTv) as TextView
        //为控件设置属性
        textView.text = message
        titleTv.text = title

        //Toast的初始化
        val toastStart = Toast(context)
        //获取屏幕高度
        val wm =
                context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val height = wm.defaultDisplay.height

        //Toast的Y坐标是屏幕高度的1/3，不会出现不适配的问题
        toastStart.setGravity(Gravity.TOP, 0, height / 3)
        toastStart.duration = Toast.LENGTH_LONG
        toastStart.view = toastRoot
        toastStart.show()
    }


    fun showErrorDialog(context: Context, message: String) {
        //加载Toast布局
        val toastRoot: View = LayoutInflater.from(context).inflate(R.layout.toast_error_dialog, null)
        //初始化布局控件
        val textView = toastRoot.findViewById(R.id.msgTv) as TextView
        //为控件设置属性
        textView.text = message
        //Toast的初始化
        val toastStart = Toast(context)
        //获取屏幕高度
        val wm =
                context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val height = wm.defaultDisplay.height
        //Toast的Y坐标是屏幕高度的1/3，不会出现不适配的问题
        toastStart.setGravity(Gravity.TOP, 0, height / 3)
        toastStart.duration = Toast.LENGTH_LONG
        toastStart.view = toastRoot
        toastStart.show()
    }
}