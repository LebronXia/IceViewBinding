package com.pince.lib_viewbinding.viewbind

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.pince.lib_viewbinding.ext.bindMethod
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by zxb in 2021/6/15
 */
class ViewHolderViewBinding<T: ViewBinding>(clazz: Class<T>): ReadOnlyProperty<RecyclerView.ViewHolder, T> {

    private var viewBinding: T? = null
    private val bindView = clazz.bindMethod()

    override fun getValue(thisRef: RecyclerView.ViewHolder, property: KProperty<*>): T {
        return viewBinding?.run {
            this
        } ?: let {
            val bind = bindView.invoke(null, thisRef.itemView) as T
            bind.apply { viewBinding = this }
        }
    }

    private fun destroyed() {
        viewBinding = null
    }

}