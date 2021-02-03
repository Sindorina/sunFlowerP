package com.sin.sunflowerp.adapter

import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.text.bold
import androidx.core.text.italic
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sin.sunflowerp.R

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(view.context)
            .load(it)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("isGone")
fun bindIsGone(view:FloatingActionButton,isGone:Boolean?){
    if (isGone == null || isGone){
        view.hide()
    }else{
        view.show()
    }
}


@BindingAdapter("wateringText")
fun bindWateringText(view: TextView,wateringInterval:Int){
    val resource = view.context.resources
    val quantityString = resource.getQuantityString(R.plurals.watering_needs_suffix,wateringInterval,wateringInterval)
    view.text = SpannableStringBuilder()
        .bold { append(resource.getString(R.string.watering_needs_prefix)) }
        .append(" ")
        .italic { append(quantityString) }
}

@BindingAdapter("renderHtml")
fun bindRenderHtml(view:TextView,description:String?){
    if (description == null){
        view.text = ""
    }else{
        view.text = HtmlCompat.fromHtml(description,HtmlCompat.FROM_HTML_MODE_COMPACT)
        view.movementMethod = LinkMovementMethod.getInstance()
    }
}