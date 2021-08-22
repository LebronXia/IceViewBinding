package com.pince.ice_viewbinding

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.pince.ice_viewbinding.databinding.ActivityMainBinding
import com.pince.lib_viewbinding.ext.viewbind
import com.pince.lib_viewbinding.viewbind.ActivityViewBinding

class MainActivity : AppCompatActivity() {

    val binding: ActivityMainBinding by viewbind()


    //val bindingV2 by viewbindV2 { ActivityMainBinding::bind }
    var picUrl: ArrayList<String> = ArrayList()
    val locationString: Array<String> = arrayOf(
            "image_1",
            "image_2",
            "image_3",
            "image_4",
            "image_5",
            "image_6",
            "image_7",
            "image_8"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.tvHahaha.text = "哈哈哈哈哈"

//        for (i in 0..10){
//            picUrl.add("http://test.izs.static.qingangpt.com//20201250//1607333417DhqXvt.webp")
//        }
//        Glide.with(this).asBitmap()
//                .load(picUrl[0])
//                .override(100,100)
//                .into(object : SimpleTarget<Bitmap?>() {
//                    override fun onResourceReady(
//                            resource: Bitmap,
//                            transition: Transition<in Bitmap?>?
//                    ) {
//                        binding.lottieAnimationView.updateBitmap(locationString[0], resource)
//                    }
//                })
//
//
//        binding.lottieAnimationView.setAnimation("data.json")
//        binding.lottieAnimationView.imageAssetsFolder = "images"
//        binding.lottieAnimationView.repeatCount = -1
//        binding.lottieAnimationView.playAnimation()
    }


}

