package com.example.moviesapp.ui.moduleone.fragment.viewmodel

import com.example.moviesapp.ni.remote.response.Results

interface IMovieItemListener {
    fun onMovieItemClick(result: Results)
}