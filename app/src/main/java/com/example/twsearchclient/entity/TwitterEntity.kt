package com.example.twsearchclient.entity

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.twsearchclient.common.GenericAdapterEntity
import com.example.twsearchclient.R

class TwitterEntity : GenericAdapterEntity() {

    var name: String? = null
    var handle: String? = null
    var favoriteCount: Int = 0
    var retweetCount: Int = 0
    var profileImageUrl: String = ""
    var text: String? = null


    companion object {
        @SuppressLint("CheckResult")
        @JvmStatic
        @BindingAdapter(value = ["setImageUrl"])
        fun loadImage(view: ImageView, url: String) {
            val requestOption = RequestOptions()
                .placeholder(R.drawable.twitter_fail).centerCrop()

            if (url.isNotEmpty()) {
                Glide.with(view.context).load(url)
                    .transition(GenericTransitionOptions.with(android.R.anim.slide_in_left))
                    .apply(requestOption)
                    .apply(RequestOptions.circleCropTransform())
                    .error(view.context.getResources().getDrawable(R.drawable.twitter_fail))
                    .listener(object : RequestListener<Drawable?> {
                        override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable?>, isFirstResource: Boolean): Boolean {
                            return false
                        }
                        override fun onResourceReady(resource: Drawable?, model: Any, target: Target<Drawable?>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                            return false
                        }
                    }).into(view)

            }
        }
    }
}