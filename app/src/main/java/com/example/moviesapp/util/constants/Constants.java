package com.example.moviesapp.util.constants;

/**
 * Created by Darshan Patel 24/02/2020
 * Usage: list down all application constants here
 * How to call: call through class name and variable as all variable inside this class would be static
 */
public class Constants {
    public static final String CRASHLYTICS_KEY_PRIORITY = "priority";
    public static final String CRASHLYTICS_KEY_TAG = "tag";
    public static final String CRASHLYTICS_KEY_MESSAGE = "message";

    // Retrofit file cache name
    public static String retrofitCacheFile = "investerServiceCacheFile";

    public interface ApiEndpoints {
        String MAIN_API = "movie?";
    }

    public interface ApiFields {
        String CONTENT_TYPE = "application";
        String API_KEY = "api_key";
        String API_KEY_VALUE = "3556ca776065513190ed0e8239318864";
        String LANGUAGE  = "language";
        String LANGUAGE_VALUE  = "en-US";
        String SORT_PARAM = "sort_by";
        String SORT_BY_POPULARITY  = "popularity.desc";
        String SORT_BY_RATING  = "vote_average.desc";
        String ADULT_PARAM = "include_adult";
        String ADULT_VALUE = "false";
        String VIDEO_PARAM = "include_video";
        String PAGE_PARAM = "page";
    }
}
