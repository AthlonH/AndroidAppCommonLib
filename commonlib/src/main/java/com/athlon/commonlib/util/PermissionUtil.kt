package com.athlon.commonlib.util

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Process
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker

object PermissionUtil {


    fun checkLocationPermissionGranted(context: Context): Boolean {
        return (checkPermissionGranted(context, Manifest.permission.ACCESS_FINE_LOCATION)
                || checkPermissionGranted(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ))
    }


    fun requestPermission(
        activity: Activity,
        permissions: Array<String>
    ) {
        ActivityCompat.requestPermissions(
            activity,
            permissions, 1
        )
    }


    @SuppressLint("WrongConstant")
    fun checkPermissionGranted(
        context: Context,
        permission: String
    ): Boolean {
        return PermissionChecker.checkPermission(
            context,
            permission, Process.myPid(),
            Process.myUid(), context.packageName
        ) == PackageManager.PERMISSION_GRANTED
//        return context.checkPermission(permission, Process.myPid(), Process.myUid()) == PackageManager.PERMISSION_GRANTED;
    }
}