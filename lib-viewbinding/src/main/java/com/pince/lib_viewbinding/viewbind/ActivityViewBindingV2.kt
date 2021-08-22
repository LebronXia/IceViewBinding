package com.pince.lib_viewbinding.viewbind

import android.app.Activity
import android.view.View
import androidx.viewbinding.ViewBinding
import com.pince.lib_viewbinding.ActivityDelegate
import com.pince.lib_viewbinding.ext.inflateMethod
import kotlin.reflect.KProperty

/**
 * Created by zxb in 2021/6/17
 */
class ActivityViewBindingV2< T: ViewBinding>(val activity: Activity,
                                            val viewBinder: (View) -> T

) : ActivityDelegate<T>(activity){
    override fun getValue(thisRef: Activity, property: KProperty<*>): T {


        //viewBinding 不为空说明已经绑定，直接返回
        return viewBinding?.run {this}?: let {
            // 当继承 Activity 且 Build.VERSION.SDK_INT < Build.VERSION_CODES.Q 时触发
            addLifecycleFragment(activity)

            //获取bind对象  ,invoke()方法执行
            val bind = viewBinder(thisRef as View)

            //setContentView(binding.root)
            thisRef.setContentView(bind.root)
            //缓存绑定类对象
            return bind.apply { viewBinding = this }
        }
    }
}