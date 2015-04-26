package com.abing.jokenorris.helper;

import android.net.Uri;

public class URLHelper {

    private static final String SCHECME = "http";
    private static final String AUTHORITY = "api.icndb.com";
    private static final String PATH_JOKE = "jokes";
    private static final String PATH_RANDOM = "random";

    private static final String PARAM_FIRST_NAME = "firstName";
    private static final String PARAM_LAST_NAME = "lastName";

    private static final String PARAM_LIMIT_TO = "limitTo";

    private static Uri.Builder getMainUri(){
        return new Uri.Builder()
                .scheme(SCHECME)
                .authority(AUTHORITY)
                .appendPath(PATH_JOKE)
                .appendPath(PATH_RANDOM);
    }

    public static String getRandomJokeURL(){
        return getMainUri().build().toString();
    }

    public static  String getNameJokeURL(String firstName, String lastName){
        return getMainUri()
                .appendQueryParameter(PARAM_FIRST_NAME, firstName)
                .appendQueryParameter(PARAM_LAST_NAME, lastName)
                .build().toString();
    }

    public static String getCategoryJokeURL(String category){
        return getMainUri()
                .appendQueryParameter(PARAM_LIMIT_TO, "[" + category + "]")
                .build().toString();
    }
}
