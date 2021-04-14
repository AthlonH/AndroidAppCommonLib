package com.athlon.commonlib.util

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager

object AppUtil {


    /**
     * 通过包名启动应用
     * 如果需要启动的应用在任务栈中，则直接启动的这个应用的任务栈的顶端 activity
     * 否则启动 MainActivity
     */
    fun launchApp(context: Context, packageName: String) {

        val packageManager = context.packageManager
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        intent?.let {
            it.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED or Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(it)
        }

//        var mainActivity: String? = null
//        val intent = Intent(Intent.ACTION_MAIN)
//        intent.addCategory(Intent.CATEGORY_LAUNCHER)
//        intent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED or Intent.FLAG_ACTIVITY_NEW_TASK)
//        @SuppressLint("WrongConstant")val activities =
//            packageManager.queryIntentActivities(intent, PackageManager.GET_ACTIVITIES)
//        for (info in activities) {
//            if (info.activityInfo.packageName.equals(packageName)) {
//                mainActivity = info.activityInfo.name
//                break
//            }
//        }
//        if (mainActivity.isNullOrEmpty()) {
//            return
//        }
//        intent.setComponent(ComponentName(packageName, mainActivity))
//        context.startActivity(intent)
    }

}