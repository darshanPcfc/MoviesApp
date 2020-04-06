package com.example.moviesapp.ni.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviesapp.ni.remote.MainApiRequest
import com.example.moviesapp.ni.remote.response.DiscoverMovieResponse
import com.example.moviesapp.ni.retrofit.MainApiInterface
import com.example.moviesapp.ni.retrofit.RetrofitGenericResponse
import com.example.moviesapp.ni.retrofit.RetrofitResponseCallback
import com.example.moviesapp.util.constants.Constants
import com.google.gson.Gson
import retrofit2.Response

/**
 * Created by Darshan Patel 24/02/2020
 * Usage: call group of api for main module
 * How to call: inject it through coin
 * Useful parameter: context and apiinterface instance to call api
 */
class MainApiRepository(private val context: Context, val mainApiInterceptor: MainApiInterface) {
    fun moviesData(sort: String, pageNumber: String): LiveData<DiscoverMovieResponse> {
        val data = MutableLiveData<DiscoverMovieResponse>()

        RetrofitGenericResponse.callRetrofit(mainApiInterceptor.mainApiCall(
            Constants.ApiFields.API_KEY_VALUE,
            Constants.ApiFields.LANGUAGE_VALUE,
            sort,
            Constants.ApiFields.ADULT_VALUE,
            Constants.ApiFields.VIDEO_PARAM,
            pageNumber
        ), object :
            RetrofitResponseCallback {
            override fun success(response: Response<*>) {
                if (response.body() != null) {
                    println(response.body().toString())
                    data.value = response.body() as DiscoverMovieResponse
                }
            }

            override fun error(error: String) {
                data.value = null
            }

            override fun failure(message: String) {
                data.value = null
            }
        })
        return data
    }

    companion object {
        private val TAG = MainApiRepository::class.java.simpleName
    }
}