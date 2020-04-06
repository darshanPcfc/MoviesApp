package com.example.moviesapp.ni.retrofit

import com.example.moviesapp.ni.remote.response.DiscoverMovieResponse
import com.example.moviesapp.util.constants.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Darshan Patel 24/02/2020
 * Usage: list all your network api here withtheir request and response parameter
 * How to call: with instance of MainApiInterface and call method to access the api
 * Useful parameter: response and request classes of respective methods
 */
interface MainApiInterface {

    @GET(Constants.ApiEndpoints.MAIN_API)
    fun mainApiCall(
        @Query(Constants.ApiFields.API_KEY) strApiKey: String,
        @Query(Constants.ApiFields.LANGUAGE) strLanguage: String,
        @Query(Constants.ApiFields.SORT_PARAM) strSortOrder: String,
        @Query(Constants.ApiFields.ADULT_PARAM) strAdult: String,
        @Query(Constants.ApiFields.VIDEO_PARAM) strVideo: String,
        @Query(Constants.ApiFields.PAGE_PARAM) strPageNumber: String
    ): Call<DiscoverMovieResponse>
}