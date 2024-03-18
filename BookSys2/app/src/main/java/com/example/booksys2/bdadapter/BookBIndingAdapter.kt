package com.example.booksys2.bdadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.booksys2.R

@BindingAdapter("imageBehiver")
fun imageBindingAdapter(image:ImageView,url:String){
    image.load(
        url
    ){
        placeholder(R.mipmap.ic_launcher)
        crossfade(true)
        error(R.mipmap.ic_launcher)
    }
}