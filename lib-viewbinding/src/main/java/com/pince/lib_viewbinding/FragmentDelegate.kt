package com.pince.lib_viewbinding

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.pince.lib_viewbinding.ext.observerWhenCreated
import com.pince.lib_viewbinding.ext.observerWhenDestroyed
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by zxb in 2021/6/11
 */
abstract class FragmentDelegate<T: ViewBinding>(fragment: Fragment)
    : ReadOnlyProperty<Fragment, T>{

    protected var viewBinding: T? = null

    init {
        //如果视图生命周期为 DESTROYED，说明视图被销毁，此时不缓存绑定类对象（避免内存泄漏）
        fragment.lifecycle.observerWhenCreated {
            fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewOwner ->
                viewOwner.lifecycle.observerWhenDestroyed {
                    destroyed()
                }
            }
        }
    }

    private fun destroyed(){
        viewBinding = null
    }

}