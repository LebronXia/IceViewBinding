package com.pince.lib_viewbinding.ext

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.app.Fragment
import android.os.Build
import android.os.Bundle
import androidx.lifecycle.BindingLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * Created by zxb in 2021/6/11
 */
//生命周期监听
fun Lifecycle.observerWhenDestroyed(destroyed: () -> Unit) {
    addObserver(LifecycleObserver(lifecycle = this, destroyed = destroyed))
}

fun Lifecycle.observerWhenCreated(create: () -> Unit) {
    addObserver(LifecycleObserver(lifecycle = this, create = create))
}

class LifecycleObserver(
    var lifecycle: Lifecycle?,
    var destroyed:(() -> Unit) ?= null,
    var create: (() -> Unit) ?= null): BindingLifecycleObserver(){

    override fun onCreate(owner: LifecycleOwner?) {
        super.onCreate(owner)
        create?.invoke()
    }

    override fun onDestroy(owner: LifecycleOwner?) {
        super.onDestroy(owner)
        destroyed?.invoke()
    }
}

//全局生命周期
fun Activity.observerWhenDestroyed(destroyed: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        registerActivityLifecycleCallbacks(LifecycleCallbacks(destroyed))
    }
}

class LifecycleCallbacks(var destroyed: (() -> Unit)? = null) :
    Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        destroyed?.invoke()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            activity.unregisterActivityLifecycleCallbacks(this)
        }
        destroyed = null
    }
}

class LifecycleFragment : Fragment {
    var destroyed: (() -> Unit)? = null

    constructor()

    @SuppressLint("ValidFragment")
    constructor(destroyed: () -> Unit) : this() {
        this.destroyed = destroyed
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyed?.invoke()
        destroyed = null
    }
}



