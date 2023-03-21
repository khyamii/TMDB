@file:Suppress("DEPRECATION")

package com.example.tmdb.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.example.tmdb.utilities.GlobalVariables


@BindingAdapter(value = ["listImage"])
fun loadListImage(view: ImageView, imageUrl: String? = "") {
    if (imageUrl != null && imageUrl != "") {


        Glide.with(view.context).load(GlobalVariables.BaseMoviePosterUrl+imageUrl)
            .apply( RequestOptions().override(800, 800))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .transition(DrawableTransitionOptions.withCrossFade(1000))
            .into(object : SimpleTarget<Drawable?>() {

                override fun onResourceReady(
                    resource: Drawable,
                    transition: com.bumptech.glide.request.transition.Transition<in Drawable?>?
                ) {
                    view.background = resource

                }
            })
    }



}


