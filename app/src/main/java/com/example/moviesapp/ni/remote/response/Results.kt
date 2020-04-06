package com.example.moviesapp.ni.remote.response

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import java.io.Serializable

data class Results(
    val popularity: Double,
    val vote_count: Int,
    val video: Boolean,
    val poster_path: String?,
    val id: Int,
    val adult: Boolean,
    val backdrop_path: String?,
    val original_language: String,
    val original_title: String,
    val genre_ids: ArrayList<Int>,
    val title: String,
    val vote_average: Double,
    val overview: String,
    val release_date: String
) : Serializable {
    companion object {
        // important code for loading image here
        @JvmStatic
        @BindingAdapter("bindposter")
        fun loadImage(display_image: ImageView, poster_path: String?) {
            if(poster_path!=null) {
                Glide.with(display_image.getContext())
                    .load("https://image.tmdb.org/t/p/w500" + poster_path)
                    .placeholder(R.drawable.ic_broken_image_black_48dp)
                    .into(display_image)
            }else{
                display_image.setImageResource(R.drawable.ic_broken_image_black_48dp)
            }
        }
    }

}
