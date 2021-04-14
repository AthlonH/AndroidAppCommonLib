package com.athlon.commonlib

import androidx.annotation.IdRes
import androidx.annotation.LayoutRes


@Target(AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
@MustBeDocumented
annotation class LayoutId(@LayoutRes @IdRes val id: Int)

