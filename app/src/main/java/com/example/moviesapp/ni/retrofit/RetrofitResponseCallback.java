package com.example.moviesapp.ni.retrofit;


import retrofit2.Response;

public interface RetrofitResponseCallback {
    void success(Response response);
    void error(String error);
    void failure(String message);
}
