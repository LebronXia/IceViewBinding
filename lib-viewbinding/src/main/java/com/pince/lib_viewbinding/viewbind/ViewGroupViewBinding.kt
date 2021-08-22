package com.pince.lib_viewbinding.viewbind

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.pince.lib_viewbinding.ext.inflateMethod
import com.pince.lib_viewbinding.ext.inflateMethodWithViewGroup
import java.lang.reflect.Method
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by zxb in 2021/6/15
 */
class ViewGroupViewBinding<T: ViewBinding>(clazz: Class<T>,
    val inflater: LayoutInflater,
    val viewGroup: ViewGroup?= null): ReadOnlyProperty<ViewGroup, T> {

    private var viewBinding : T ?= null
    private var layoutInflater: Method

    init {
        if (viewGroup != null){
            layoutInflater = clazz.inflateMethodWithViewGroup()
        } else {
            layoutInflater = clazz.inflateMethod()
        }
    }
    override fun getValue(thisRef: ViewGroup, property: KProperty<*>): T {
        return viewBinding?.run {
            this
        } ?: let{
            val bind : T
            if (viewGroup != null) {
                bind = layoutInflater.invoke(null, inflater, viewGroup) as T
            } else {
                bind = layoutInflater.invoke(null, inflater) as T
            }
            bind.apply {
                if (viewGroup == null){
                    thisRef.addView(bind.root)
                }
                viewBinding = this
            }
        }

    }

    private fun destroyed(){
        viewBinding = null
    }


}