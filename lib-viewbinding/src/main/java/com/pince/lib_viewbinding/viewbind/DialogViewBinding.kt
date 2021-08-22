package com.pince.lib_viewbinding.viewbind

import android.app.Dialog
import androidx.lifecycle.Lifecycle
import androidx.viewbinding.ViewBinding
import com.pince.lib_viewbinding.DialogDelegate
import com.pince.lib_viewbinding.ext.inflateMethod
import kotlin.reflect.KProperty

/**
 * Created by zxb in 2021/6/11
 */
class DialogViewBinding<T: ViewBinding>(val classes: Class<T>,
                                        lifecycle: Lifecycle? =null) : DialogDelegate<T>(lifecycle){

    override fun getValue(thisRef: Dialog, property: KProperty<*>): T {

        var layoutInflater = classes.inflateMethod()

        return viewBinding?.run {
            this
        }?: let {
            val bind = layoutInflater.invoke(null, thisRef.layoutInflater) as T
            thisRef.setContentView(bind.root)
            return bind.apply { viewBinding = this }
        }
    }

}