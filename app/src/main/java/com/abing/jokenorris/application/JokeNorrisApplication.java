package com.abing.jokenorris.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by Catingub on 4/23/2015.
 */
public class JokeNorrisApplication extends Application{

    private static JokeNorrisApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static JokeNorrisApplication getApplication(){
        return sInstance;
    }

    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }
}
