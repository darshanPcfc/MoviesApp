package com.example.moviesapp.ni.remote.response

data class DiscoverMovieResponse(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results : ArrayList<Results>
)