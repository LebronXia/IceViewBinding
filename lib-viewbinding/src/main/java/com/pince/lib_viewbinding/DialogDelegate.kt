package com.pince.lib_viewbinding

import android.app.Dialog
import androidx.lifecycle.Lifecycle
import androidx.viewbinding.ViewBinding
import com.pince.lib_viewbinding.ext.observerWhenDestroyed
import kotlin.properties.ReadOnlyProperty

/**
 * Created by zxb in 2021/6/11
 */
abstract class DialogDelegate<T: ViewBinding>(
    lifecycle: Lifecycle? = null) : ReadOnlyProperty<Dialog, T>{

    protected var viewBinding: T? = null
    init {
        lifecycle?.observerWhenDestroyed { destroyed() }
    }
    fun destroyed() {
        viewBinding = null
    }
}