package com.pince.lib_viewbinding.ext

import android.app.Activity
import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.pince.lib_viewbinding.viewbind.*

/**
 * Created by zxb in 2021/6/15
 */

inline fun <reified T : ViewBinding> Activity.viewbind() =
        ActivityViewBinding(T::class.java, this)

//inline fun <reified T : ViewBinding> Activity.viewbindV2( viewBinder: (View) -> T) =
//        ActivityViewBindingV2(this, viewBinder)

inline fun <reified T : ViewBinding> Fragment.viewbind() =
        FragmentViewBinding(T::class.java, this)

inline fun <reified T : ViewBinding> Dialog.viewbind() =
        DialogViewBinding(T::class.java)

inline fun <reified T : ViewBinding> Dialog.viewbind(lifecycle: Lifecycle) =
        DialogViewBinding(T::class.java, lifecycle)

inline fun <reified T : ViewBinding> RecyclerView.ViewHolder.viewbind() =
        ViewHolderViewBinding(T::class.java)

inline fun <reified T : ViewBinding> ViewGroup.viewbind() = ViewGroupViewBinding(
        T::class.java,
        LayoutInflater.from(getContext())
)

inline fun <reified T : ViewBinding> ViewGroup.viewbind(viewGroup: ViewGroup) =
        ViewGroupViewBinding(
                T::class.java,
                LayoutInflater.from(getContext()),
                viewGroup
        )