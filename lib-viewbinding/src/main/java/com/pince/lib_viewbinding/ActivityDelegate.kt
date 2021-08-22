package com.pince.lib_viewbinding

import android.app.Activity
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import com.pince.lib_viewbinding.ext.LifecycleFragment
import com.pince.lib_viewbinding.ext.observerWhenDestroyed
import kotlin.properties.ReadOnlyProperty

/**
 * Created by zxb in 2021/6/11
 */
abstract class ActivityDelegate<T: ViewBinding>(activity: Activity)
    : ReadOnlyProperty<Activity, T> {
    //ReadOnlyProperty是不可变属性代理

    protected var viewBinding: T? = null
    private val LIFECYCLE_FRAGMENT_TAG = "com.pience.binding.lifecycle_fragment"

    init {
        when (activity) {
            //防止内存泄露进行销毁
            is ComponentActivity -> activity.lifecycle.observerWhenDestroyed { destroyed()}
                else -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        activity.observerWhenDestroyed { destroyed() }
                    }
                }
            }
    }

    /**
     * 当继承 Activity 且 Build.VERSION.SDK_INT < Build.VERSION_CODES.Q 以下的时候，
     * 会添加一个 空白的 Fragment, 当生命周期处于 onDestroy 时销毁数据
     */
    fun addLifecycleFragment(activity: Activity) {
        if (activity is FragmentActivity || activity is AppCompatActivity) return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) return
        val fragmentManager = activity.fragmentManager
        if (fragmentManager.findFragmentByTag(LIFECYCLE_FRAGMENT_TAG) == null) {
            val transaction = fragmentManager.beginTransaction()
            transaction.add(LifecycleFragment { destroyed() }, LIFECYCLE_FRAGMENT_TAG).commit()
            fragmentManager.executePendingTransactions()
        }
    }

    private fun destroyed(){
        viewBinding = null
    }

}