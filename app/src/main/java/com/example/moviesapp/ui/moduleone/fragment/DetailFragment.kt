package com.example.moviesapp.ui.moduleone.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.moviesapp.BR
import com.example.moviesapp.R
import com.example.moviesapp.base.BaseFragment
import com.example.moviesapp.databinding.FragmentDetailBinding
import com.example.moviesapp.ni.remote.response.Results
import com.example.moviesapp.ui.moduleone.activities.MainActivity
import com.example.moviesapp.ui.moduleone.fragment.viewmodel.DetailViewModel
import com.example.moviesapp.ui.moduleone.fragment.viewmodel.IDetailObserver
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Darshan Patel
 * Usage: to display detail of movies
 */
class DetailFragment  : BaseFragment<FragmentDetailBinding, DetailViewModel>(),
    IDetailObserver {
    override val viewModel: DetailViewModel by viewModel()

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_detail

    lateinit var fragmentDetailBinding: FragmentDetailBinding
    lateinit var resultData: Results

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
        val args by navArgs<DetailFragmentArgs>()
        resultData = args.item
        Log.e(DetailFragment::javaClass.name,"result >> " + resultData.backdrop_path)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentDetailBinding = this.viewDataBinding!!
        fragmentDetailBinding.data = resultData
        setBackgrounPoster()
    }

    private fun setBackgrounPoster() {
        if(resultData.backdrop_path!=null) {
            loadbackImage(iv_background,resultData.backdrop_path)
        }else{
            iv_background.setImageResource(R.drawable.ic_broken_image_black_48dp)
        }
    }

    fun loadbackImage(display_image: ImageView, backdrop_path: String?) {
        Glide.with(display_image.getContext())
            .load("https://image.tmdb.org/t/p/w780" + backdrop_path)
            .placeholder(R.drawable.ic_broken_image_black_48dp)
            .into(display_image)
    }
}