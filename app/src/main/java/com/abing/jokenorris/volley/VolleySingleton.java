package com.abing.jokenorris.volley;

import com.abing.jokenorris.application.JokeNorrisApplication;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Catingub on 4/23/2015.
 */
public class VolleySingleton {

    public static VolleySingleton sInstance = null;

    private RequestQueue mRequestQueue;

    private VolleySingleton() {
        mRequestQueue = Volley.newRequestQueue(JokeNorrisApplication.getAppContext());
    }

    public static VolleySingleton getsInstance() {
        if (sInstance == null) {
            sInstance = new VolleySingleton();
        }
        return sInstance;
    }

    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }
}
