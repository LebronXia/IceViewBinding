package com.pince.lib_viewbinding.viewbind

import android.app.Activity
import androidx.viewbinding.ViewBinding
import com.pince.lib_viewbinding.ActivityDelegate
import com.pince.lib_viewbinding.ext.inflateMethod
import kotlin.reflect.KProperty

/**
 * Created by zxb in 2021/6/11
 * https://blog.csdn.net/u011976726/article/details/78639708
 * https://juejin.cn/post/6960914424865488932
 * https://github.com/hi-dhl/Binding
 */
class ActivityViewBinding<T: ViewBinding>(val classes: Class<T>,
    val activity: Activity) : ActivityDelegate<T>(activity){

    override fun getValue(thisRef: Activity, property: KProperty<*>): T {

        //ActivityTestBinding.inflate(layoutInflater)
        var layoutInflater = classes.inflateMethod()

        //viewBinding 不为空说明已经绑定，直接返回
        return viewBinding?.run {this}?: let {
            // 当继承 Activity 且 Build.VERSION.SDK_INT < Build.VERSION_CODES.Q 时触发
            addLifecycleFragment(activity)

            //获取bind对象  ,invoke()方法执行
            val bind = layoutInflater.invoke(null, thisRef.layoutInflater) as T

            //setContentView(binding.root)
            thisRef.setContentView(bind.root)
            //缓存绑定类对象
            return bind.apply { viewBinding = this }
        }
    }

}