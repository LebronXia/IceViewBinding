package com.pince.lib_viewbinding.viewbind

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.pince.lib_viewbinding.FragmentDelegate
import com.pince.lib_viewbinding.ext.bindMethod
import com.pince.lib_viewbinding.ext.inflateMethod
import kotlin.reflect.KProperty

/**
 * Created by zxb in 2021/6/11
 */
class FragmentViewBinding<T: ViewBinding>(clazz: Class<T>, fragment: Fragment): FragmentDelegate<T>(fragment) {

    private val layoutInflater = clazz.inflateMethod()
    private val bindView = clazz.bindMethod()

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {

        return viewBinding?.run {
            return this
        } ?: let {
            val bind: T
            if (thisRef.view == null){
                bind = layoutInflater.invoke(null, thisRef.layoutInflater) as T
            } else {
                //FragmentTestBinding.bind(root)
                bind = bindView.invoke(null, thisRef.view) as T
            }

            return bind.apply { viewBinding == this }
        }
    }

}